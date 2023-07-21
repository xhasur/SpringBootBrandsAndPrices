package com.shop.inditex.domain.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class AuthService {
    private static final Algorithm ALGORITHM = Algorithm.HMAC256("t0ken_tok3n");

    private static Logger LOGGER = LogManager.getLogger(AuthService.class);

    public String create(String username) {
        return JWT.create()
                .withSubject(username)
                .withIssuer("api")
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(2)))
                .sign(ALGORITHM);
    }

    public boolean isValid(String jwt) {
        try {
            JWT.require(ALGORITHM)
                    .build()
                    .verify(jwt);
        } catch (JWTVerificationException e) {
            LOGGER.info("TOKEN IS INVALID");
            return false;
        }
        LOGGER.info("TOKEN IS VALID");
        return true;
    }

    public String getUsername(String jwt) {
        return JWT.require(ALGORITHM)
                .build()
                .verify(jwt)
                .getSubject();
    }
}
