package com.ecommerce.API.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Service
public class JWTService {
    private final SecretKey secretKey;
    private final Set<String> invalidatedTokens = new HashSet<>();
    @Autowired
    private ApplicationContext context;

    public JWTService() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
            secretKey = keyGenerator.generateKey();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public String generateToken(String email) {
        Map<String, Object> claims = new HashMap<>();


        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(email)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .and()
                .signWith(secretKey)
                .compact();
    }


    public UsernamePasswordAuthenticationToken validateToken(String token, Claims claims) {
        if (invalidatedTokens.contains(token)) {
            return null;
        }

        // If the expiration date was before now
        if (claims.getExpiration() == null || claims.getExpiration().before(new Date())) {
            return null;
        }

        UserDetails userDetails;
        // Check whether the username exists
        try {
            userDetails = context.getBean(CustomerDetailsService.class).loadUserByUsername(claims.getSubject());
        } catch (UsernameNotFoundException usernameNotFoundException) {
            return null;
        }

        return new UsernamePasswordAuthenticationToken(userDetails, token, userDetails.getAuthorities());
    }

    public Claims extractClaims(String token) throws SignatureException {
        Jws<Claims> jws = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token);
        return jws.getPayload();
    }

    public boolean invalidateToken(String token) {
        try {
            // Check if the token is valid by attempting to extract the claims
            Claims claims = extractClaims(token);
            invalidatedTokens.add(token);
        } catch (SignatureException signatureException) {
            return false;
        }
        return true;
    }
}
