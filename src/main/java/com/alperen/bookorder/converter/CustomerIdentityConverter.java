package com.alperen.bookorder.converter;

import com.alperen.bookorder.model.CustomerIdentity;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class CustomerIdentityConverter {

    public CustomerIdentity apply(Claims claims) {
        CustomerIdentity customerIdentity = new CustomerIdentity();
        customerIdentity.setId(getValue(claims, "id"));
        customerIdentity.setName(getValue(claims, "name"));
        customerIdentity.setSurname(getValue(claims, "surname"));
        customerIdentity.setEmail(getValue(claims, "email"));
        return customerIdentity;
    }

    private String getValue(Claims claims, String key) {
        Object foundValue = claims.getOrDefault(key, StringUtils.EMPTY);
        return foundValue == null ? null : foundValue.toString();
    }
}
