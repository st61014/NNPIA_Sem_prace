package nnpia.st61014.NNPIA_SemPrace.controller;

import nnpia.st61014.NNPIA_SemPrace.NnpiaSemPraceApplication;
import nnpia.st61014.NNPIA_SemPrace.domain.AppUser;
import nnpia.st61014.NNPIA_SemPrace.repository.AppUserRepository;
import nnpia.st61014.NNPIA_SemPrace.service.JWTGenerator;
import nnpia.st61014.NNPIA_SemPrace.service.UsersInterestedInJobService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = NnpiaSemPraceApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.yml")
class UsersInterestedInJobControllerTest {
    @Autowired
    AppUserRepository appUserRepository;
    @MockBean
    AuthenticationManager authenticationManager;
    @Autowired
    private MockMvc mvc;
    @Autowired
    JWTGenerator jwtGenerator;
    @MockBean
    private UsersInterestedInJobService service;
    Page<Object[]> pageData = Mockito.mock(Page.class);;
    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        AppUser appUser = new AppUser("username", "password", "name", "surname", "field");

        appUserRepository.save(appUser);
    }

    @AfterEach
    void tearDown() {
        appUserRepository.deleteAll();
    }
    @Test
    void getInterestsByUserId() throws Exception {

        Collection< GrantedAuthority > auths = new ArrayList<>();
        auths.add(new SimpleGrantedAuthority("USER"));
        UserDetails userDetails = new User("username","password", auths);
        Authentication auth = new UsernamePasswordAuthenticationToken (userDetails.getUsername (),userDetails.getPassword (),userDetails.getAuthorities ());

        SecurityContextHolder.getContext().setAuthentication(auth);
        Long userID = appUserRepository.findAppUserByUsername("username").get().getUserID();
        String token = jwtGenerator.generateToken(auth, userID);

        //TODO: stavba objeku do metody
        Object[] contentInPage = new Object[6];
        contentInPage[0] = 200;
        contentInPage[1] = "tech company";
        contentInPage[2] = "chief engineer";
        contentInPage[3] = 12000;
        contentInPage[4] = "2023-05-23T17:34:55.294";
        contentInPage[5] = "open";
        pageData.toSet();

        given(service.findInterestsByUserIdWithJobDetails(1L, 0, "pay")).willReturn(pageData);

        mvc.perform(get("/job-listing/all?page=0&sort=pay").header(HttpHeaders.AUTHORIZATION, "Bearer "+token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
    }
}