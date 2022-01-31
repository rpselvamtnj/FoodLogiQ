package com.food.logiq.util.exception;

import static org.springframework.http.HttpStatus.CONFLICT;

/**
 * Exception class for conflict data.
 *
 * @author Rotation5
 */
public class ConflictException extends CustomException {

    public ConflictException(String errorCode) {
        super(CONFLICT, errorCode);
    }

    public ConflictException(String errorCode, Throwable throwable) {
        super(CONFLICT, errorCode, throwable);
    }
}
