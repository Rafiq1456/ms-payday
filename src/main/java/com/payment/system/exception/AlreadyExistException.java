package com.payment.system.exception;

import lombok.Getter;


@Getter
public class AlreadyExistException extends RuntimeException {
    private final String message;

    public AlreadyExistException(String message) {
        super(message);
        this.message = message;
    }

}
