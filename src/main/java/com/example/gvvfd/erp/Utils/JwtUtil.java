package com.example.gvvfd.erp.Utils;

import com.example.gvvfd.erp.Models.enums.UserRole;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {
    public String generateToken(UserDetails Deets, UserRole Role) {
        return generateToken(new HashMap<>(), Deets, Role);
    }

    public String generateToken(Map<String, Object> additionalClaims, UserDetails Deets, UserRole Role) {

        return Jwts.builder().setClaims(additionalClaims)
                .setSubject(Deets.getUsername())
                .setAudience(Role.name())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();

    }

    public Key getSigningKey() {
        String base64String = "CandyrollTechRobloxERPDev";  // Original string

        // Pad or truncate to ensure it is exactly 32 bytes (256 bits) long
        while (base64String.length() < 32) {
            base64String += "0";  // Example padding with '0' to make it 32 bytes
        }

        byte[] keyBytes = base64String.getBytes(StandardCharsets.UTF_8);  // Convert to bytes
        return Keys.hmacShaKeyFor(keyBytes);  // Generate HMAC key
    }

    public boolean isTokenValid(String jwt, UserDetails Deets) {
        final String Username = extractUserName(jwt);
        return (Username.equals(Deets.getUsername())) && !isTokenExpired(jwt);
    }

    public boolean isTokenExpired(String jwt) {
        return extractExpiration(jwt).before(new Date());
    }

    public String extractUserName(String jwt) {
        return extractClaim(jwt, Claims::getSubject);
    }

    public Date extractExpiration(String jwt){
        return extractClaim(jwt, Claims::getExpiration);
    }

    public <T> T extractClaim(String jwt, Function<Claims, T> claimsResolvers) {
        final Claims claims = extractAllClaims(jwt);
        return claimsResolvers.apply(claims);
    }

    public Claims extractAllClaims(String jwt) {
        return Jwts.parserBuilder().setSigningKey(getSigningKey())
                .build().parseClaimsJws(jwt)
                .getBody();
    }
}
