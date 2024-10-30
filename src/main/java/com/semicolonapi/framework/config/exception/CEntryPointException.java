package com.semicolonapi.framework.config.exception;

public class CEntryPointException extends RuntimeException {
    public CEntryPointException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

    public CEntryPointException(String msg) {
        super(msg);
    }

    public CEntryPointException() {
        super();
    }
}
