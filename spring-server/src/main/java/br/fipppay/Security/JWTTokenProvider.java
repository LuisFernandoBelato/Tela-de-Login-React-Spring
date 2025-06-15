package br.fipppay.Security;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;


public class JWTTokenProvider {
    private static final SecretKey KEY = Keys.hmacShaKeyFor(
            "SECRET_KEY_EXEMPLE/SECRET_KEY_EXEMPLE".getBytes(StandardCharsets.UTF_8));

    // ANOTHER EXAMPLE
    //private static final SecretKey KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256); // HS256 ITS A ALGORITM

    static public String getToken(String level)
    {       
        String jwtToken = Jwts.builder()
            .setSubject("SystemUser")
            .setIssuer("localhost:8080")
            .claim("level", level)
            .setIssuedAt(new Date())
            .setExpiration(Date.from(LocalDateTime.now().plusMinutes(15L)
                .atZone(ZoneId.systemDefault()).toInstant()))
            .signWith(KEY)
            .compact();
        return jwtToken;        
    }

    static public boolean verifyToken(String token)
    {
        try {
            Jwts.parserBuilder()
                .setSigningKey(KEY)
                .build()
                .parseClaimsJws(token).getSignature();
                return true;
       } catch (Exception e) {
                System.out.println(e);
       }
       return false;       
    }

    static public Claims getAllClaimsFromToken(String token) 
    {
        Claims claims=null;
        try {
            claims = Jwts.parserBuilder()
            .setSigningKey(KEY)
            .build()
            .parseClaimsJws(token)
            .getBody();
        } catch (Exception e) {
            System.out.println("Error to Reclaim the (claims)");
        }
        return claims;        
    }

}
