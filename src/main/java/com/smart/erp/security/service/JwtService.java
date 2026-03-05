package com.smart.erp.security.service;

import com.smart.erp.security.config.JwtConfig;
import com.smart.erp.security.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final JwtConfig jwtConfig;

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(jwtConfig.getSecret().getBytes(StandardCharsets.UTF_8));
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails, User user) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("userId", user.getUserId());
        extraClaims.put("lang", user.getLanguagePreference());
        return generateToken(extraClaims, userDetails, user);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails, User user) {
        long expirationMinutes = user.getExpirationMinutes() != null ?
                user.getExpirationMinutes() : jwtConfig.getExpirationMinutes();

        return Jwts.builder()
                .claims(extraClaims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expirationMinutes * 60 * 1000))
                .signWith(getSigningKey())
                .compact();
    }

    public long getExpirationTime(User user) {
        long expirationMinutes = user.getExpirationMinutes() != null ?
                user.getExpirationMinutes() : jwtConfig.getExpirationMinutes();
        return expirationMinutes * 60 * 1000;
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}