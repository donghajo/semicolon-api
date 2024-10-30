package com.semicolonapi.framework.config.exception;

public class CAccessDeniedException extends RuntimeException{
    public CAccessDeniedException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

    public CAccessDeniedException(String msg) {
        super(msg);
    }

    public CAccessDeniedException() {
        super();
    }
}
