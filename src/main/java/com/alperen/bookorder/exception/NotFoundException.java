package com.alperen.bookorder.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String name, String id) {
        super(name + " is not found by id: " + id);
    }
}
