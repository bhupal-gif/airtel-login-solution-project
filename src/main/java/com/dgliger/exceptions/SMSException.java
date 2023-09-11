package com.dgliger.exceptions;

public class SMSException extends RuntimeException {

    public SMSException(String message) {
        super(message);
    }

    public SMSException(String message, Throwable ex) {
        super(message, ex);
    }

}
