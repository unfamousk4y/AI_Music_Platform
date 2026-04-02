package com.unfamousk4y.musicplatform.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class JwtUtil {

    private static final byte[] SECRET = "your-super-secret-key-that-is-long-enough-32chars".getBytes();
    private static final long EXPIRATION = 86400000;

    public String generateToken(String email) {
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(Keys.hmacShaKeyFor(SECRET))
                .compact();
    }

    public String extractEmail(String token) {
        return getClaims(token).getSubject();
    }

    public boolean validateToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(SECRET))
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}