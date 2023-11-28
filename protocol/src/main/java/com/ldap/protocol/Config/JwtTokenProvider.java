package com.ldap.protocol.Config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long validityInMilliseconds;

    @Value("${jwt.refreshExpiration}")
    private long refreshValidityInMilliseconds;

    public JwtTokenProvider(@Value("${jwt.secret}") String secretKey,
                            @Value("${jwt.expiration}") long validityInMilliseconds,
                            @Value("${jwt.refreshExpiration}") long refreshValidityInMilliseconds) {
        this.secretKey = secretKey;
        this.validityInMilliseconds = validityInMilliseconds;
        this.refreshValidityInMilliseconds = refreshValidityInMilliseconds;
    }

    public JwtTokenProvider() {
    }

    // Generate Access Token
    public String createAccessToken(String userDetails) {
        return generateToken(userDetails, validityInMilliseconds);
    }

    private String generateToken(String userDetails, long validityInMilliseconds) {
        return generateToken(userDetails,validityInMilliseconds);
    }

    // Generate Refresh Token
    public String createRefreshToken(String userDetails) {
        return generateToken(userDetails, refreshValidityInMilliseconds);
    }

    // Generate Token Helper Method
    private String generateToken(UserDetails userDetails, long validity) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + validity))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    // Validate Token
    public boolean validateToken(String token) {
        return !isTokenExpired(token);
    }

    // Extract Username from Token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Extract Claims from Token
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Extract All Claims from Token
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    // Check if Token is Expired
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Extract Expiration Date from Token
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}

