package com.apb15.neorekruit.security.filter;

import com.apb15.neorekruit.dto.pengguna.JWTResponse;
import com.apb15.neorekruit.dto.pengguna.LoginRequest;
import com.apb15.neorekruit.security.JWTUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            LoginRequest credentials = new ObjectMapper().readValue(
                    request.getInputStream(),
                    LoginRequest.class
            );

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    credentials.getEmail(),
                    credentials.getPassword()
            );
        return authenticationManager.authenticate(authenticationToken);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authentication) throws IOException,
            ServletException {
        User pengguna = (User)authentication.getPrincipal();

        String subject = pengguna.getUsername();
        String issuer = request.getRequestURL().toString();
        List<String> claims = pengguna.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(
                        Collectors.toList());

        String accessToken = JWTUtils.generateAccessToken(subject, issuer, claims);
        String refreshToken = JWTUtils.generateRefreshToken(subject, issuer);

        JWTResponse jwtResponse = JWTResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();

        response.setContentType(APPLICATION_JSON_VALUE);
        response.getWriter().write(jwtResponse.toString());
    }
}
