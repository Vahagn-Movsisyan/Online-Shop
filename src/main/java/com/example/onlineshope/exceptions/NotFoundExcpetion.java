package com.example.onlineshope.exceptions;

public class NotFoundExcpetion extends RuntimeException {
    public NotFoundExcpetion() {
    }

    public NotFoundExcpetion(String message) {
        super(message);
    }

    public NotFoundExcpetion(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundExcpetion(Throwable cause) {
        super(cause);
    }

    public NotFoundExcpetion(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
