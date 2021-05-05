package com.alperen.bookorder.exception;

public class OrderAccessException extends RuntimeException {

    public static final String ACCESS_IS_DENIED = "Order access is denied!";

    public OrderAccessException() {
        super(ACCESS_IS_DENIED);
    }
}
