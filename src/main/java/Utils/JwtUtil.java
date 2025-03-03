package Utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {
    public String generateToken(UserDetails Deets) {
        return generateToken(new HashMap<>(), Deets);
    }

    public String generateToken(Map<String, Object> additionalClaims, UserDetails Deets) {
        return Jwts.builder().setClaims(additionalClaims)
                .setSubject(Deets.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();

    }

    public Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode("jdfosjfpojpeojfowfpoejpowjfpojosjdf;ldsfm32po432j3940ui09#$#:JF:LSD:FL");
        return Keys.hmacShaKeyFor(keyBytes);
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
