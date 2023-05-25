package nnpia.st61014.NNPIA_SemPrace.controller;

import nnpia.st61014.NNPIA_SemPrace.NnpiaSemPraceApplication;
import nnpia.st61014.NNPIA_SemPrace.domain.AppUser;
import nnpia.st61014.NNPIA_SemPrace.repository.AppUserRepository;
import nnpia.st61014.NNPIA_SemPrace.service.JWTGenerator;
import nnpia.st61014.NNPIA_SemPrace.service.AppUserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = NnpiaSemPraceApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.yml")
class AuthControllerTest {
    @MockBean
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTGenerator jwtGenerator;
    @Autowired
    AppUserRepository appUserRepository;
    @Autowired
    AppUserService appUserService;

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
    void testRequestTokenAndReadContents() {
        Collection< GrantedAuthority > auths = new ArrayList<>();
        auths.add(new SimpleGrantedAuthority("USER"));
        UserDetails userDetails = new User("username","password", auths);
        Authentication auth = new UsernamePasswordAuthenticationToken (userDetails.getUsername (),userDetails.getPassword (),userDetails.getAuthorities ());

        SecurityContextHolder.getContext().setAuthentication(auth);
        Long userID = appUserRepository.findAppUserByUsername("username").get().getUserID();

        String token = jwtGenerator.generateToken(auth, userID);
        assertEquals("username", jwtGenerator.getUsernameFromJWT(token));
        assertEquals(1L, Long.parseLong(jwtGenerator.getIdFromJWT(token)));
    }
}