package nnpia.st61014.NNPIA_SemPrace.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.Console;
import java.io.IOException;
@RequiredArgsConstructor
@Component
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(      HttpServletRequest request,
                               HttpServletResponse response,
                               AuthenticationException authException)
            throws IOException, ServletException {

        HttpStatus status = HttpStatus.UNAUTHORIZED;
        //log.error("Failed Authentication: {}", authException.getMessage(), authException);
        response.setStatus(status.value());
        response.sendError(status.value(), "Unauthorized Access");
    }
/*
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
    }
    */
}
