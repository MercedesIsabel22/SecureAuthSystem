package com.isacode.infrastructure.security.Jwt;

import com.isacode.infrastructure.security.ConstKeywords;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtil {

    private String token_secreto = ConstKeywords.SECRET_TOKEN;
    public Claims extractClaims(String token){
        return Jwts.parser().setSigningKey(token_secreto).parseClaimsJws(token).getBody();
    }
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractClaims(token);
        return claimsResolver.apply(claims);
    }
    public Date extractExpiration(String token){
        return extractClaim(token, (Claims claims)-> claims.getExpiration());
    }
    public String extractUsername(String token){
        return extractClaim(token, (Claims claims)-> claims.getSubject());
    }
    public Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    public String createToken(Map<String, Object> claims, String subject)
    {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 60*60*60*10))
                .signWith(SignatureAlgorithm.HS256, token_secreto).compact();
    }
    public String generateToken(String username, String role){
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);
        return createToken(claims, username);
    }
    public Boolean validateToken(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

}
