
package com.aips.traffic.exception;

public class InvalidInfoException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InvalidInfoException(String message) {
        super(message);
    }

    public InvalidInfoException(String message, Throwable cause) {
        super(message, cause);
    }
}
