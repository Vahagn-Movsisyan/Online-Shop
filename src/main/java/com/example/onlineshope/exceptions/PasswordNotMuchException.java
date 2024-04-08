package com.example.onlineshope.exceptions;

public class PasswordNotMuchException extends RuntimeException {
    public PasswordNotMuchException() {
    }

    public PasswordNotMuchException(String message) {
        super(message);
    }

    public PasswordNotMuchException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordNotMuchException(Throwable cause) {
        super(cause);
    }
}
