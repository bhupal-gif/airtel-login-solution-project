package com.dgliger.exceptions;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.dgliger.exceptions.ErrorCode.M00G0001;

public class BadRequestException extends RuntimeException {

    private final UUID errorId;
    private final ErrorCode errorCode;
    private final List<ErrorInfo> errors = new ArrayList<>();

    public BadRequestException(String message) {
        this(message, UUID.randomUUID(), M00G0001);
    }

    public BadRequestException(String message, UUID errorId, ErrorCode errorCode) {
        super(message);
        this.errorId = errorId;
        this.errorCode = errorCode;
    }


}
