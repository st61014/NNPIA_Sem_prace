package nnpia.st61014.NNPIA_SemPrace.controller;

import nnpia.st61014.NNPIA_SemPrace.dto.AuthResponseDto;
import nnpia.st61014.NNPIA_SemPrace.security.JWTGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AuthControllerTest {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTGenerator jwtGenerator;

    @Test
    void testRequestTokenAndReadContents() {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        "user1",
                        "pass1"));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Long userID = 1L;
        String token = jwtGenerator.generateToken(authentication, userID);
        assertEquals("user1", jwtGenerator.getUsernameFromJWT(token));
        assertEquals(1L, Long.parseLong(jwtGenerator.getIdFromJWT(token)));
    }
}