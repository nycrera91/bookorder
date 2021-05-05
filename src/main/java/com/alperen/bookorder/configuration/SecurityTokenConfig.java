package com.alperen.bookorder.configuration;

import com.alperen.bookorder.exception.TokenException;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

@Configuration
public class SecurityTokenConfig {

    private String key = "Vbfert34cbg345123Csd2h45gbDS67vb3q5zS547VGnbr";

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public SecretKeySpec getSecretKeySpec() {
        try {
            return new SecretKeySpec(getKey().getBytes(StandardCharsets.UTF_8), SignatureAlgorithm.HS512.getJcaName());
        } catch (Exception e) {
            throw new TokenException(e);
        }
    }

}
