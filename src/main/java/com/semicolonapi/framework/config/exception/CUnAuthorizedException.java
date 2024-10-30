package com.semicolonapi.framework.config.exception;

public class CUnAuthorizedException extends RuntimeException {
    public CUnAuthorizedException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

    public CUnAuthorizedException(String msg) {
        super(msg);
    }

    public CUnAuthorizedException() {
        super();
    }
}
