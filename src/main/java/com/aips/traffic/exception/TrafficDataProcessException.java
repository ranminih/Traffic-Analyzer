
package com.aips.traffic.exception;

public class TrafficDataProcessException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public TrafficDataProcessException(String message) {
        super(message);
    }

    public TrafficDataProcessException(String message, Throwable cause) {
        super(message, cause);
    }
}
