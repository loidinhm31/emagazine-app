package com.emagazine.api.rest.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class RestExceptionHandler {

    // Exception handler for ObjectNotFoundException
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(ObjectNotFoundException exc) {

        ErrorResponse error = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                exc.getMessage(),
                System.currentTimeMillis());
        log.error(exc.toString(), exc);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // Exception handler to catch any exception (catch all)
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(Exception exc) {

        ErrorResponse error = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                exc.getMessage(),
                System.currentTimeMillis());
        log.error(exc.toString(), exc);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
