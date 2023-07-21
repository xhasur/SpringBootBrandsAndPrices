package com.shop.inditex.domain.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.shop.inditex.domain.dto.LoginDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class AuthService {
    private static Algorithm ALGORITHM = Algorithm.HMAC256("t0ken_tok3n");

    private final AuthenticationManager authenticationManager;

    private static Logger LOGGER = LogManager.getLogger(AuthService.class);

    public AuthService(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


    public String generateToken(LoginDto loginDto) {
        UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());
        Authentication authentication = this.authenticationManager.authenticate(login);
        LOGGER.info(authentication.isAuthenticated());
        LOGGER.info(authentication.getPrincipal());
        return this.create(loginDto.getUsername());

    }

    public String create(String username) {
        return JWT.create()
                .withSubject(username)
                .withIssuer("api")
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(15)))
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
