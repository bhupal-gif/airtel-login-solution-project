package com.dgliger.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.UUID;

import static com.dgliger.exceptions.ErrorResponse.*;
import static java.lang.String.format;


@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse exceptionHandler(final EntityNotFoundException ex) {
        UUID errorId = logException(ex, ex.getErrorId(), false);
        return notFound(errorId, ErrorCode.M00G0007, ex.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse exceptionHandler(final UserNotFoundException ex) {
        UUID errorId = logException(ex, false);
        return notFound(errorId, ErrorCode.M00G0001, ex.getMessage());
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse exceptionHandler(ValidationException e) {
        UUID errorId = logException(e, false);
        return validation(errorId, ErrorCode.M00G0001, e.getMessage());
    }

    @ExceptionHandler(UserNotActiveException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse exceptionHandler(UserNotActiveException e) {
        UUID errorId = logException(e, false);
        return validation(errorId, ErrorCode.M00G0006, e.getLocalizedMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse exceptionHandler(BadRequestException e) {
        UUID errorId = logException(e, false);
        return validation(errorId, ErrorCode.M00G0008, e.getLocalizedMessage());
    }

    @ExceptionHandler(EntityAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse exceptionHandler(EntityAlreadyExistException e) {
        UUID errorId = logException(e, false);
        return alreadyExist(errorId, ErrorCode.M00G0001, HttpStatus.CONFLICT, e.getLocalizedMessage());
    }

    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse exceptionHandler(DuplicateKeyException e) {
        UUID errorId = logException(e, false);
        return alreadyExist(errorId, ErrorCode.M00G0001, HttpStatus.CONFLICT, e.getLocalizedMessage());
    }


    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse exceptionHandler(AuthenticationException ex) {
        UUID errorId = logException(ex, false);
        return authentication(errorId, ErrorCode.M00G0000, ex.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse exceptionHandler(AccessDeniedException ex) {
        UUID errorId = logException(ex, false);
        return authorization(errorId, ErrorCode.M00G0003, ex.getMessage());
    }

    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse exceptionHandler(ForbiddenException ex) {
        UUID errorId = logException(ex, false);
        return authorization(errorId, ErrorCode.M00G0003, ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse generalExceptionHandler(final Exception ex) {
        UUID errorId = logException(ex, false);
        return server(errorId, ErrorCode.M00G0002, ex.getMessage());
    }

    private <E extends Throwable> UUID logException(E exception, boolean printTrace) {
        return logException(exception, UUID.randomUUID(), printTrace);
    }

    private <E extends Throwable> UUID logException(E exception, UUID errorId, boolean printTrace) {
        String message =
                format(
                        "%s Caught - Error id %s. %s",
                        exception.getClass().getSimpleName(), errorId, exception.getMessage());
        if (printTrace) log.error(message, exception);
        else log.error(message);
        return errorId;
    }
}
