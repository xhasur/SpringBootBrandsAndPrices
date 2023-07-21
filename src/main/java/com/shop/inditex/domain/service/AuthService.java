package com.shop.inditex.domain.service;

import com.shop.inditex.domain.dto.LoginDto;
import com.shop.inditex.web.config.JwtUtil;
import com.shop.inditex.web.controller.PriceController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    private static Logger LOGGER = LogManager.getLogger(AuthService.class);

    public AuthService(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }


    public String generateToken(LoginDto loginDto) {        UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());
        Authentication authentication = this.authenticationManager.authenticate(login);
        LOGGER.info(authentication.isAuthenticated());
        LOGGER.info(authentication.getPrincipal());
        return this.jwtUtil.create(loginDto.getUsername());

    }
}
