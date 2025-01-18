package com.bank.banksystemapi.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

@Service
public class JWTService {
    @Value("${application.security.jwt.secret-key}")
    private String SECRET_KEY;

    @Value("${application.security.jwt.expiration}")
    private Long jwtEpiration;


    public String getUserEmail(String jwtToken) {
        return extractClaim(jwtToken, Claims::getSubject);
    }

    // get expiration date from token
    private Date extractExpiration(String jwtToken) {
        return extractClaim(jwtToken, Claims::getExpiration);
    }

    public String generateToken(
            Map<String, Objects> extraClaims,
            UserDetails userDetails
    ){

        String authority = userDetails.getAuthorities().stream()
                .findFirst()
                .map(GrantedAuthority::getAuthority)
                .orElse("");

        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtEpiration) )
                .claim("authorities", authority)
                .signWith(getSignKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    public String generateToken( UserDetails userDetails){
        return generateToken(new HashMap<>(),userDetails);
    }
    private <T> T extractClaim(String jwtToken, Function<Claims,T> claimResolver) {
        final Claims allClaims = extractAllClaims(jwtToken);
        return claimResolver.apply(allClaims);
    }

    private Claims extractAllClaims(String jwtToken) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();
    }

    private boolean isTokenExpired(String jwtToken) {
        return extractExpiration(jwtToken).before(new Date());
    }

    public boolean isTokenValid(String jwtToken, UserDetails userDetails) {
        String userEmail = getUserEmail(jwtToken);
        return userEmail.equals(userDetails.getUsername()) && !isTokenExpired(jwtToken) ;
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }


}
