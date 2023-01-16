package com.mrgoropeza.portfoliobackend.security;

import java.util.Collections;
import java.util.Date;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class TokenUtils {

    public static String createToken(String name) {
        long expirationTime = Long.parseLong(JwtProperties.validity) * 1000;
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);

        return Jwts.builder()
                .setSubject(name)
                .setExpiration(expirationDate)
                .signWith(Keys.hmacShaKeyFor(JwtProperties.secret.getBytes()))
                .compact();
    }

    public static UsernamePasswordAuthenticationToken getAuthentication(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(JwtProperties.secret.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            String name = claims.getSubject();

            return new UsernamePasswordAuthenticationToken(name, null, Collections.emptyList());
        } catch (JwtException e) {
            System.out.println("\n\nauthentication error:");
            System.out.println(e + "\n\n");
            return null;
        }
    }

}
