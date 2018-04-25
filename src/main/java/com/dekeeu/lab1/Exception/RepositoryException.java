package com.dekeeu.lab1.Exception;

/**
 * Created by dekeeu on 13/03/2018.
 */
public class RepositoryException extends Exception {
    public RepositoryException() {
        super();
    }

    public RepositoryException(String message) {
        super(message);

    }

    public RepositoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public RepositoryException(Throwable cause) {
        super(cause);
    }

    protected RepositoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
