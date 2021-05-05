package com.alperen.bookorder.controller;

import com.alperen.bookorder.auth.AuthService;
import com.alperen.bookorder.dto.CustomerAuthenticationDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public CustomerAuthenticationDTO getToken(@RequestBody CustomerAuthenticationDTO customerAuthenticationDTO) {
        return authService.getToken(customerAuthenticationDTO);
    }
}
