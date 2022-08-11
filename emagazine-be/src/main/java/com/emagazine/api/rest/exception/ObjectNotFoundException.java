package com.emagazine.api.rest.exception;

public class ObjectNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 4603746191850353273L;

    public ObjectNotFoundException() {

    }

    public ObjectNotFoundException(String message) {
        super(message);

    }

    public ObjectNotFoundException(Throwable cause) {
        super(cause);

    }

    public ObjectNotFoundException(String message, Throwable cause) {
        super(message, cause);

    }

    public ObjectNotFoundException(String message, Throwable cause, boolean enableSuppression,
                                   boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);

    }

}
