package com.example.ms_security.util;

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

    private static  final String SECRET="hb3J1ZW9idTQzNW5zdGF1In0Khb3J1ZW9idTQzNW5zdGF1In0Khb3J1ZW9idTQzNW5zdGF1In0Khb3J1ZW9idTQzNW5zdGF1In0Khb3J1ZW9idTQzNW5zdGF1In0Khb3J1ZW9idTQzNW5zdGF1In0Khb3J1ZW9idTQzNW5zdGF1In0Khb3J1ZW9idTQzNW5zdGF1In0K";

    public String extractUsername(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaims(token, Claims::getExpiration);
    }

    public <T> T extractClaims(String token, Function<Claims,T> claimsResolver) {
        final Claims claims=extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    public Key getSignKey(){
        byte[] keyBytes= Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String email=extractUsername(token);
        return (email.equals(userDetails.getUsername())&&!isTokenExpired(token));

    }

    public String generateToken(String email){
        Map<String, Object> claims=new HashMap<>();
        return createToken(claims,email);
    }

    public String createToken(Map<String,Object> claims, String subject){
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*24))
                .signWith(SignatureAlgorithm.HS256,getSignKey())
                .compact();
    }

    public Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
}
