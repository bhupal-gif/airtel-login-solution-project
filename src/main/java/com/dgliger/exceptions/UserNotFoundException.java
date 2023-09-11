package com.dgliger.exceptions;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.dgliger.exceptions.ErrorCode.M00G0005;

public class UserNotFoundException extends RuntimeException {

    private final UUID errorId;
    private final ErrorCode errorCode;
    private final List<ErrorInfo> errors = new ArrayList<>();

    public UserNotFoundException(String message) {
        this(message, UUID.randomUUID(), M00G0005);
    }

    public UserNotFoundException(String message, UUID errorId, ErrorCode errorCode) {
        super(message);
        this.errorId = errorId;
        this.errorCode = errorCode;
    }

    public UserNotFoundException(ErrorResponse errorResponse) {
        super(errorResponse.getMessage());
        this.errorId = errorResponse.getErrorId();
        this.errorCode = errorResponse.getErrorCode();
        if (errorResponse.getErrors() != null) {
            withInfo(errorResponse.getErrors());
        }
    }

    public UserNotFoundException withInfo(ErrorInfo info) {
        errors.add(info);
        return this;
    }

    public UserNotFoundException withInfo(List<ErrorInfo> info) {
        errors.addAll(info);
        return this;
    }

}

