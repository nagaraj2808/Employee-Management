package com.org.employee.employee_management.config.jwt;

import com.org.employee.employee_management.models.Employee;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class TokenGenerator {
    private final String secret_key = "mysecretkeymysecretkeymysecretkeymysecretkeymysecretkeymysecretkeymysecretkeymysecretkeymysecretkeymysecretkey";
    private long accessTokenValidity = 60*60*1000;

    private final JwtParser jwtParser;

    private final String TOKEN_HEADER = "Authorization";
    private final String TOKEN_PREFIX = "" +
            "Bearer ";

    public TokenGenerator() {
        this.jwtParser = Jwts.parser().setSigningKey(secret_key);
    }

    public String getSubject(Claims claims){
        return claims.getSubject();
    }

    public String createToken(Employee employee){
        Claims claims = Jwts.claims().setSubject(employee.getEmail());
        claims.put("roles",employee.getAuthorities());
        Date tokenCreateTime = new Date();
        Date tokenValidity = new Date(tokenCreateTime.getTime() + TimeUnit.MINUTES.toMillis(accessTokenValidity));
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(tokenValidity)
                .signWith(SignatureAlgorithm.HS256, secret_key)
                .compact();

    }

    public Claims validateToken(String token)  {
        try{
            Claims claims =  jwtParser.parseClaimsJws(token).getBody();
            System.out.println(claims);
            return claims;

        }
        catch (Exception e){
            throw e;
        }

    }
}
