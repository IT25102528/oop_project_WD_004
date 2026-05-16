package com.project001.Regency.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JWTUtil {

    private final String SECRET =
            "mysecretkeymysecretkeymysecretkey12345";

    private final long EXPIRATION = 1000 * 60 * 60 * 24;

    private Key getSignKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    // GENERATE TOKEN
    public String generateToken(String email, String role) {

        return Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(System.currentTimeMillis() + EXPIRATION)
                )
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // EXTRACT EMAIL
    public String extractEmail(String token) {

        return extractAllClaims(token).getSubject();
    }

    // EXTRACT ROLE
    public String extractRole(String token) {

        return extractAllClaims(token).get("role", String.class);
    }

    // VALIDATE TOKEN
    public boolean validateToken(String token, String email) {

        final String extractedEmail = extractEmail(token);

        return extractedEmail.equals(email)
                && !isTokenExpired(token);
    }

    // CHECK EXPIRATION
    private boolean isTokenExpired(String token) {

        return extractAllClaims(token)
                .getExpiration()
                .before(new Date());
    }

    // EXTRACT CLAIMS
    private Claims extractAllClaims(String token) {

        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}