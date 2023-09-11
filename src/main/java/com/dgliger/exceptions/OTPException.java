package com.dgliger.exceptions;

public class OTPException extends RuntimeException {

    public OTPException(String message) {
        super(message);
    }

    public OTPException(String message, Throwable ex) {
        super(message, ex);
    }

}
