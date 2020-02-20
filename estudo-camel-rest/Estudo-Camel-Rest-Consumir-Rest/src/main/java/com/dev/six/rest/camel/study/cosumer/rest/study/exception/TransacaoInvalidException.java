package com.dev.six.rest.camel.study.cosumer.rest.study.exception;

public class TransacaoInvalidException extends Exception {
    public TransacaoInvalidException(String message) {
        super(message);
    }
    public TransacaoInvalidException(String message, Throwable cause) {
        super(message, cause);
    }
}
