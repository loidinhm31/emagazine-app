package com.emagazine.web.exception;

import java.io.IOException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;


@ControllerAdvice
public class ClientDataTransferExceptionHandler {

    // Exception handler to catch any exception (catch all)
    @ExceptionHandler
    public ModelAndView handleException(HttpClientErrorException exc) {


        String errString = exc.getResponseBodyAsString();

        ErrorResponse errorResponse = null;
        try {
            errorResponse = new ObjectMapper().readValue(errString, ErrorResponse.class);

        } catch (IOException e) {

        }

        ModelAndView errorView = new ModelAndView();
        errorView.setViewName("error/404");
        errorView.addObject("errorData", errorResponse.getMessage());

        return errorView;
    }

}
