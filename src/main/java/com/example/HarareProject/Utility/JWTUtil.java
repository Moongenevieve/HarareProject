package com.example.HarareProject.Utility;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.util.Date;


@Component
public class JWTUtil {

    private final long EXPIRATIONTIME = 1000*60*60;   //1 hour
    private final String Secret = "tttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt"; //at least 32 digits
    private final SecretKey key = Keys.hmacShaKeyFor(Secret.getBytes());


    public String generateToken(String username) {

        return  Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRATIONTIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }


    public String extractUserNameFromToken (String token) {
        Claims body = extractClaims(token);
        return body.getSubject();
    }

    private Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validateToken(String username, UserDetails userDetails,String token) {
        //check if username is same as username in user details
        return username.equals(userDetails.getUsername()) && !IsTokenExpired(token);
        //check if token is not expired

    }

    private boolean IsTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }
}

