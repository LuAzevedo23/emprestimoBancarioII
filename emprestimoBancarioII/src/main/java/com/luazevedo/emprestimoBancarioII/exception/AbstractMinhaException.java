package com.luazevedo.emprestimoBancarioII.exception;

public class AbstractMinhaException extends RuntimeException {

    public AbstractMinhaException(String message) {
        super(message);
    }

    public AbstractMinhaException(String message, Throwable cause) {
        super(message, cause);
    }
}
