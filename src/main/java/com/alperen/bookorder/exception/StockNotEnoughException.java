package com.alperen.bookorder.exception;

public class StockNotEnoughException extends RuntimeException {

    public static final String STOCK_IS_NOT_ENOUGH = "Stock is not enough!";

    public StockNotEnoughException() {
        super(STOCK_IS_NOT_ENOUGH);
    }
}
