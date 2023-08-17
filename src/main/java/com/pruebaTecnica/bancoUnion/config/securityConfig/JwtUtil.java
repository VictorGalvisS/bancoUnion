package com.pruebaTecnica.bancoUnion.config.securityConfig;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtil {

    private static String SECRET_KEY = "tecnicalTest_bancoUnion";
    private static Algorithm ALGORITM = Algorithm.HMAC256(SECRET_KEY);

    public String create(String username) {
        return JWT.create()
                .withSubject(username)
                .withIssuer("bancoUnion")
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(15)))
                .sign(ALGORITM);
    }

    public boolean isValid(String jwt) {
        try {
            JWT.require(ALGORITM).build().verify(jwt);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    public String getUsername(String jwt) {
        return JWT.require(ALGORITM).build().verify(jwt).getSubject();
    }
}
