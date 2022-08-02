package com.apb15.neorekruit.controller;

import com.apb15.neorekruit.dto.ErrorResponse;
import com.apb15.neorekruit.dto.pengguna.JWTResponse;
import com.apb15.neorekruit.model.Pengguna;
import com.apb15.neorekruit.model.Role;
import com.apb15.neorekruit.security.JWTUtils;
import com.apb15.neorekruit.service.PenggunaService;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
public class TokenController {

    private final PenggunaService penggunaService;

    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                try {
                    String refreshToken = authorizationHeader.substring("Bearer ". length());

                    DecodedJWT decodedJWTRefresh = JWTUtils.decodeJWTToken(refreshToken);
                    String email = decodedJWTRefresh.getSubject();
                    Pengguna pengguna = penggunaService.getPengguna(email);

                    String subject = pengguna.getEmail();
                    String issuer = request.getRequestURL().toString();
                    List<String> claims = pengguna.getRoles().stream().map(Role::getName).collect(
                                    Collectors.toList());

                    String accessToken = JWTUtils.generateAccessToken(subject, issuer, claims);

                    JWTResponse jwtResponse = JWTResponse.builder()
                            .accessToken(accessToken)
                            .refreshToken(refreshToken)
                            .build();

                    response.setContentType(APPLICATION_JSON_VALUE);
                    response.getWriter().write(jwtResponse.toString());

                } catch (Exception exception) {
                    System.out.println("Error");

                    ErrorResponse error = ErrorResponse.builder()
                            .errorMsg(exception.getMessage())
                            .build();

                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    response.setContentType(APPLICATION_JSON_VALUE);
                    response.getWriter().write(error.toString());
                }
            }
    }
}
