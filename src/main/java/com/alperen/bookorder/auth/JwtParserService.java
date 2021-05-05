package com.alperen.bookorder.auth;

import com.alperen.bookorder.exception.TokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class JwtParserService {

    private String secretKey = "Vbfert34cbg345123Csd2h45gbDS67vb3q5zS547VGnbr";

    public Claims decodeJWT(String jwt) {
        try {
            return Jwts.parser()
                    .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))
                    .parseClaimsJws(jwt)
                    .getBody();
        } catch (Exception e) {
            throw new TokenException(e);
        }
    }

}
