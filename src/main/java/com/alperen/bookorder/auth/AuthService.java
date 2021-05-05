package com.alperen.bookorder.auth;

import com.alperen.bookorder.converter.CustomerIdentityConverter;
import com.alperen.bookorder.dto.CustomerAuthenticationDTO;
import com.alperen.bookorder.exception.AuthFailedException;
import com.alperen.bookorder.model.Customer;
import com.alperen.bookorder.model.CustomerAuthentication;
import com.alperen.bookorder.repository.CustomerRepository;
import io.jsonwebtoken.Claims;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
public class AuthService {

    private TokenService tokenService;
    private CustomerRepository customerRepository;
    private CustomerIdentityConverter customerIdentityConverter;
    private JwtTokenService jwtTokenService;

    public AuthService(TokenService tokenService, CustomerRepository customerRepository, CustomerIdentityConverter customerIdentityConverter, JwtTokenService jwtTokenService) {
        this.tokenService = tokenService;
        this.customerRepository = customerRepository;
        this.customerIdentityConverter = customerIdentityConverter;
        this.jwtTokenService = jwtTokenService;
    }


    public Authentication getUserAuthentication(HttpServletRequest httpServletRequest) {
        Claims claims = tokenService.getTokenClaims(httpServletRequest);
        return new CustomerAuthentication(customerIdentityConverter.apply(claims));
    }

    public CustomerAuthenticationDTO getToken(CustomerAuthenticationDTO customerAuthenticationDTO) {
        String encryptedPassword = DigestUtils.md5Hex(customerAuthenticationDTO.getPassword());
        Optional<Customer> authCustomer = customerRepository.findByEmailAndPassword(customerAuthenticationDTO.getEmail(), encryptedPassword);

        if (authCustomer.isEmpty()) {
            throw new AuthFailedException(customerAuthenticationDTO.getEmail());
        }
        Customer dbCustomer = authCustomer.get();


        return new CustomerAuthenticationDTO(dbCustomer.getEmail(), jwtTokenService.generate(dbCustomer), dbCustomer.getEmail(), dbCustomer.getName(), dbCustomer.getSurname());

    }

}
