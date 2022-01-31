package com.food.logiq.util.exception;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * Exception class for data not found.
 *
 * @author Rotation5
 */
public class NotFoundException extends CustomException {

    public NotFoundException(String errorCode) {
        super(NOT_FOUND, errorCode);
    }
}
