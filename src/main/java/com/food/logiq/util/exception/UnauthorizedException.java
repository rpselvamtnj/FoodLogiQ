package com.food.logiq.util.exception;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

/**
 * Exception class for UnAuthorized data.
 *
 * @author Rotation5
 */
public class UnauthorizedException extends CustomException {

    public UnauthorizedException(String errorCode) {
        super(UNAUTHORIZED, errorCode);
    }
}
