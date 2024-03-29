package com.alperen.bookorder.auth;

import com.alperen.bookorder.configuration.SecurityTokenConfig;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class JwtTokenGeneratorService {

    private SecurityTokenConfig securityTokenConfig;

    public JwtTokenGeneratorService(SecurityTokenConfig securityTokenConfig) {
        this.securityTokenConfig = securityTokenConfig;
    }

    public String generateJwtToken(DateTime expirationTime, Map<String, Object> tokenData) {
        return Jwts.builder()
                .addClaims(tokenData)
                .setExpiration(expirationTime.toDate())
                .setIssuedAt(LocalDate.now().toDate())
                .signWith(SignatureAlgorithm.HS512, securityTokenConfig.getSecretKeySpec()).compact();
    }
}
