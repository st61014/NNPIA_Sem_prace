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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.util.*;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
    Page<Object[]> pageData = Mockito.mock(Page.class);
    ;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        AppUser appUser = new AppUser("username", "password", "name", "surname", "field");

        appUserRepository.save(appUser);
    }

    private Object[] createRecordForPageContent(long id, String jobField, String position, double pay, String creationDate, String status) {
        Object[] body = new Object[6];
        body[0] = id;
        body[1] = jobField;
        body[2] = position;
        body[3] = pay;
        body[4] = creationDate;
        body[5] = status;
        return body;
    }

    @AfterEach
    void tearDown() {
        appUserRepository.deleteAll();
    }

    @Test
    void getInterestsByUserId() throws Exception {

        Collection<GrantedAuthority> auths = new ArrayList<>();
        auths.add(new SimpleGrantedAuthority("USER"));
        UserDetails userDetails = new User("username", "password", auths);
        Authentication auth = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(auth);
        Long userID = appUserRepository.findAppUserByUsername("username").get().getUserID();
        String token = jwtGenerator.generateToken(auth, userID);

        List<Object[]> recordsInContent = new ArrayList<>();
        recordsInContent.add(createRecordForPageContent(200L, "tech company", "chief engineer", 1200.0, "2023-05-23T17:34:55.294", "open"));

        given(pageData.getContent()).willReturn(recordsInContent);
        given(service.findInterestsByUserIdWithJobDetails(1L, 0, "pay")).willReturn(pageData);

        mvc.perform(get("/job-interest/user?page=0&sort=pay").header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().json("[{\"job_listing_id\":200,\"job_field\":\"tech company\",\"position\":\"chief engineer\",\"pay\":1200.0,\"creation_date\":\"2023-05-23T17:34:55.294\",\"status\":\"open\"}]"));
    }
}