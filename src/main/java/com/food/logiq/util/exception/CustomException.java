package com.food.logiq.util.exception;


import com.food.logiq.base.api.BaseBodyResponse;
import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException {

    public String errorCode;

    public HttpStatus httpStatus;

    public BaseBodyResponse baseBodyResponse;

    String message;

    public CustomException(HttpStatus httpStatus, BaseBodyResponse baseBodyResponse) {
        this.httpStatus = httpStatus;
        this.baseBodyResponse = baseBodyResponse;
    }

    public CustomException(HttpStatus httpStatus, String errorCode, Throwable ex) {
        super(ex);
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }

    public CustomException(HttpStatus httpStatus, String errorCode) {
        super();
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }

    public CustomException(HttpStatus httpStatus, String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public CustomException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }


    public String getErrorCode() {
        return this.errorCode;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public BaseBodyResponse getBaseBodyResponse() {
        return baseBodyResponse;
    }
}
