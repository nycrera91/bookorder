package com.alperen.bookorder.auth;

import com.alperen.bookorder.model.Customer;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class JwtTokenService {

    private static final String JWT_CUSTOMER_ID = "id";
    private static final String JWT_NAME = "name";
    private static final String JWT_SURNAME = "surname";
    private static final String JWT_EMAIL = "email";
    private static final int EXPIRE_YEAR = 1;

    private JwtTokenGeneratorService jwtTokenGeneratorService;

    public JwtTokenService(JwtTokenGeneratorService jwtTokenGeneratorService) {
        this.jwtTokenGeneratorService = jwtTokenGeneratorService;
    }

    public String generate(Customer customer) {
        Map<String, Object> tokenData = new HashMap<>();
        tokenData.put(JWT_CUSTOMER_ID, customer.getId());
        tokenData.put(JWT_NAME, customer.getName());
        tokenData.put(JWT_SURNAME, customer.getSurname());
        tokenData.put(JWT_EMAIL, customer.getEmail());
        DateTime expirationTime = DateTime.now().plusYears(EXPIRE_YEAR);
        return jwtTokenGeneratorService.generateJwtToken(expirationTime, tokenData);
    }
}
