package com.dgliger.exceptions;

import java.util.ArrayList;
import java.util.List;


public class SystemException extends RuntimeException {


    private final List<ErrorInfo> errors = new ArrayList<>();


    public SystemException(ErrorResponse errorResponse) {
        super(errorResponse.getMessage());

        if (errorResponse.getErrors() != null) {
            withInfo(errorResponse.getErrors());
        }
    }

    public SystemException withInfo(ErrorInfo info) {
        errors.add(info);
        return this;
    }

    public SystemException withInfo(List<ErrorInfo> info) {
        errors.addAll(info);
        return this;
    }
}
