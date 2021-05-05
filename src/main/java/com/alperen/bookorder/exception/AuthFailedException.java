package com.alperen.bookorder.exception;

import org.springframework.security.core.AuthenticationException;

public class AuthFailedException extends AuthenticationException {

    public AuthFailedException(String msg) {
        super(msg);
    }

}
