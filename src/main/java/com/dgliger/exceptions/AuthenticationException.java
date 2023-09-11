package com.dgliger.exceptions;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Getter
public class AuthenticationException extends RuntimeException {
    private final UUID errorId;
    private final ErrorCode errorCode;
    private final List<ErrorInfo> errors = new ArrayList<>();

    public AuthenticationException(String message) {
        this(message, UUID.randomUUID(), ErrorCode.M00G0003);
    }

    public AuthenticationException(String message, UUID errorId, ErrorCode errorCode) {
        super(message);
        this.errorId = errorId;
        this.errorCode = errorCode;
    }

    public AuthenticationException(ErrorResponse errorResponse) {
        super(errorResponse.getMessage());
        this.errorId = errorResponse.getErrorId();
        this.errorCode = errorResponse.getErrorCode();
        if (errorResponse.getErrors() != null) {
            withInfo(errorResponse.getErrors());
        }
    }

    public AuthenticationException withInfo(ErrorInfo info) {
        errors.add(info);
        return this;
    }

    public AuthenticationException withInfo(List<ErrorInfo> info) {
        errors.addAll(info);
        return this;
    }
}
