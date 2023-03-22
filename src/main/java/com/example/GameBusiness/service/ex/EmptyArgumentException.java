package com.example.GameBusiness.service.ex;

public class EmptyArgumentException extends ServiceException{
    public EmptyArgumentException() {
    }

    public EmptyArgumentException(String message) {
        super(message);
    }

    public EmptyArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyArgumentException(Throwable cause) {
        super(cause);
    }

    public EmptyArgumentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
