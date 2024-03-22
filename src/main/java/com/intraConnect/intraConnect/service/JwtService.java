package com.intraConnect.intraConnect.service;

import com.intraConnect.intraConnect.entity.UserEntity;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    @Value("${jwt.secret.key.app}")
    private String key;

    private final Long expirationTime = 1000L;


    public String generateToken(UserEntity user){
        Map<String, Object> claims = new HashMap<>();

        claims.put("email",user.getEmail());
        claims.put("role",user.getDepartamento().toString());

        return buildToken(user.getUsername(),user.getId().toString(),expirationTime,claims);
    }

    private String buildToken(String username, String id, Long expirationTime, Map<String, ?> claims) {
        JwtBuilder jwtBuilder = Jwts.builder()
                .setSubject(username)
                .claim("userId",id)
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(getKey(), SignatureAlgorithm.HS256);

        if (claims != null) {
            jwtBuilder.setClaims(claims);
        }

        return jwtBuilder.compact();
    }

    public String getSubject(String token) throws JwtException{
            return Jwts.parserBuilder().setSigningKey(getKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
    }

    public boolean validToken(String token){
        try {
            Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    public Claims getClaims(String token) throws JwtException{
            return Jwts.parserBuilder().setSigningKey(getKey()).build()
                    .parseClaimsJws(token).getBody();
    }

    public String getEmailClaim(String token) throws  JwtException{
        return getClaims(token).get("email",String.class);
    }


    private Key getKey(){
        byte[] keyBytes = Decoders.BASE64.decode(key);
        return Keys.hmacShaKeyFor(keyBytes);
    }



}
