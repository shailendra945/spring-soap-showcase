package com.infoiv.exception;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException(String s) {
        super(s);
    }
}
