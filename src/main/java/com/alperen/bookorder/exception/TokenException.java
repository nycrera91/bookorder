package com.alperen.bookorder.exception;

public class TokenException extends RuntimeException {

    public static final String TOKEN_IS_NOT_GENERATED_ = "Token is not generated ";

    public TokenException(Exception e) {
        super(TOKEN_IS_NOT_GENERATED_, e);
    }
}
