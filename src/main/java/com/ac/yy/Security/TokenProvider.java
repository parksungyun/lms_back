package com.ac.yy.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class TokenProvider {
    private static final String SECURITY_KEY = "jwtsecretkey!@";
    public String createJwt(int uid) {
        Date expireTime = Date.from(Instant.now().plus(1, ChronoUnit.HOURS));
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, SECURITY_KEY)
                .setSubject(String.valueOf(uid))
                .setIssuedAt(new Date())
                .setExpiration(expireTime)
                .compact();
    }

    public String validateJwt(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECURITY_KEY)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }
}
