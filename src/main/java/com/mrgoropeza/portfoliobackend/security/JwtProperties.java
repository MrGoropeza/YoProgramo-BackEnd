package com.mrgoropeza.portfoliobackend.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtProperties {

    public static String secret;
    public static String validity;

    public JwtProperties(@Value("${jwt.secret}") String secret, @Value("${jwt.validity}") String validity) {
        JwtProperties.secret = secret;
        JwtProperties.validity = validity;
    }

}
