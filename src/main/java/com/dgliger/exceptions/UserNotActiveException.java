package com.dgliger.exceptions;

public class UserNotActiveException extends RuntimeException {

    public UserNotActiveException(String message) {
        super(message);
    }

    public UserNotActiveException(String message, Throwable ex) {
        super(message, ex);
    }

}
