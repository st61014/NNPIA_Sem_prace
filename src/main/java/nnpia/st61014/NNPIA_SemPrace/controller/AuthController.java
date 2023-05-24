package nnpia.st61014.NNPIA_SemPrace.controller;

import lombok.AllArgsConstructor;
import nnpia.st61014.NNPIA_SemPrace.dto.AuthResponseDto;
import nnpia.st61014.NNPIA_SemPrace.dto.LoginDto;
import nnpia.st61014.NNPIA_SemPrace.repository.AppUserRepository;
import nnpia.st61014.NNPIA_SemPrace.security.JWTGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private AuthenticationManager authenticationManager;
    private AppUserRepository userRepository;
    private JWTGenerator jwtGenerator;


    @PostMapping("/signin")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Long userID = userRepository.findAppUserByUsername(loginDto.getUsername()).get().getUserID();
        String token = jwtGenerator.generateToken(authentication, userID);
        return new ResponseEntity<>(new AuthResponseDto(token), HttpStatus.OK);
    }

}
