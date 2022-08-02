package com.apb15.neorekruit.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.List;

public class JWTUtils {
    private static final String secret =
            "f72af0f8627ddfc26363c269ec6328ed4b43260a30a39f71bf558724d06ee73e3b348797bf9081f43d52350f209a2fdfe421aee2d39b51a4479f691b011f9c20";
    private static Algorithm algorithm = Algorithm.HMAC256(secret.getBytes());

    public static String generateAccessToken(String subject, String issuer, List<String> claims){
        return JWT.create()
                .withSubject(subject)
                .withExpiresAt(new Date(System.currentTimeMillis() + 15 * 60 * 1000))
                .withIssuer(issuer)
                .withClaim("roles", claims)
                .sign(algorithm);
    }

    public static String generateRefreshToken(String subject, String issuer) {
        return JWT.create()
                .withSubject(subject)
                .withExpiresAt(new Date(System.currentTimeMillis() + 7 * 24 * 60 * 1000))
                .withIssuer(issuer)
                .sign(algorithm);
    }

    public static DecodedJWT decodeJWTToken(String token) {
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);

        return decodedJWT;
    }
}
