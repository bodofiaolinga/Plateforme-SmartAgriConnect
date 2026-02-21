package com.example.SmartAgriConnect.security;

import com.example.SmartAgriConnect.model.User;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String secret;

    private Key getSign() {
        byte[] keyBytes= Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(User user){
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("Role",user.getRole().name())
                .claim("agriculteurId", user.getAgriculteur() != null ? user.getAgriculteur().getId().toString() : null)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000))
                .signWith(getSign(),SignatureAlgorithm.HS256)
                .compact();

    }

    public String extractEmail(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSign())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();

    }


}


