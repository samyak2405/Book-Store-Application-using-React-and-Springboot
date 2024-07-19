package com.javahunter.application.service.impl;

import com.javahunter.application.service.JWTService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;


@Service
public class JWTServiceImpl implements JWTService {
    @Override
    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    private final Logger log = LoggerFactory.getLogger(getClass().getName());
    @Override
    public String generateToken(UserDetails userDetails) {
        return Jwts.builder().setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
                .signWith(getSigninKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUserName(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    @Override
    public String generateRefreshToken(Map<String, Object> extraClaims, UserDetails user) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+604800000))
                .signWith(getSigninKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers){
        log.info("Inside extract claim");
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        log.info("Inside extract all claims");
        return Jwts.parserBuilder().setSigningKey(getSigninKey()).build().parseClaimsJws(token).getBody();
    }
    private Key getSigninKey() {
        log.info("Inside get Sign in key");
        byte[] key = Decoders.BASE64.decode("23458747EDHSQWCHR3WE7RYCEWCHFB4P8RUT34CEP98RHCFPWEIBFVC3E7YR5CP32CH2XE234E87XG3374Y1238GX7QW");
        return Keys.hmacShaKeyFor(key);
    }

    private boolean isTokenExpired(String token) {
        log.info("inside is token expired");
        return extractClaim(token,Claims::getExpiration).before(new Date());
    }
}
