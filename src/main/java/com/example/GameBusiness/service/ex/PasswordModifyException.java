package com.example.GameBusiness.service.ex;

public class PasswordModifyException extends ServiceException{
    public PasswordModifyException() {
    }
    public PasswordModifyException(String message) {
        super(message);
    }
    public PasswordModifyException(String message, Throwable cause) {
        super(message, cause);
    }
    public PasswordModifyException(Throwable cause) {
        super(cause);
    }

    public PasswordModifyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
