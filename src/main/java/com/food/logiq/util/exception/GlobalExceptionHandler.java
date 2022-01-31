package com.food.logiq.util.exception;

import com.food.logiq.base.api.BaseBodyError;
import com.food.logiq.base.api.BaseBodyError.BaseBodyDataError;
import com.food.logiq.base.api.BaseBodyResponse;
import com.food.logiq.util.ErrorCodes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;

import static com.food.logiq.util.Constants.REQUEST_NOT_READABLE_MSG;
import static com.food.logiq.util.Constants.REQUEST_UNABLE_TO_PROCESS_MSG;
import static org.springframework.http.HttpStatus.*;

/**
 * Global exception handler class for capture the exception.
 * Annotated with {@link Slf4j @Slf4j}, and {@link RestControllerAdvice @RestControllerAdvice}.
 *
 * @author Rotation5
 */
@Slf4j
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

    private final Environment env;

    public GlobalExceptionHandler(Environment env) {
        this.env = env;
    }

    /**
     * Handle http message not readable exception.
     *
     * @param exception the exception
     * @return the response entity
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public ResponseEntity<BaseBodyResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
        BaseBodyResponse<?> baseBodyResponse;
        baseBodyResponse = getBaseBodyError(ErrorCodes.REQUEST_NOT_READABLE);
        List<BaseBodyDataError> errors = getDataErrors(exception, exception.getMostSpecificCause(), REQUEST_NOT_READABLE_MSG);
        baseBodyResponse.getError().setErrors(errors);
        CustomException customException = new CustomException(BAD_REQUEST, baseBodyResponse);
        return handleCustomException(customException);
    }

    /**
     * Handle http unsupported media type.
     *
     * @param exception the exception
     * @return the response entity
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseBody
    public ResponseEntity<BaseBodyResponse> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException exception) {
        BaseBodyResponse<?> baseBodyResponse;
        baseBodyResponse = getBaseBodyError(ErrorCodes.UN_SUPPORTED_MEDIA);
        List<BaseBodyDataError> errors = getDataErrors(exception, exception.getCause(), null);
        baseBodyResponse.getError().setErrors(errors);
        CustomException customException = new CustomException(UNSUPPORTED_MEDIA_TYPE, baseBodyResponse);
        return handleCustomException(customException);
    }

    /**
     * Handle http unsupported method.
     *
     * @param exception the exception
     * @return the response entity
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public ResponseEntity<BaseBodyResponse> handleHttpRequestMethodNotSupportedException(
            HttpRequestMethodNotSupportedException exception) {
        BaseBodyResponse<?> baseBodyResponse;
        baseBodyResponse = getBaseBodyError(ErrorCodes.UN_SUPPORTED_METHOD);
        List<BaseBodyDataError> errors = getDataErrors(exception, exception.getCause(), null);
        baseBodyResponse.getError().setErrors(errors);
        CustomException customException = new CustomException(METHOD_NOT_ALLOWED, baseBodyResponse);
        return handleCustomException(customException);
    }

    /**
     * Handle method argument not valid exception.
     *
     * @param exception the exception
     * @return the response entity
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<BaseBodyResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        BaseBodyResponse<?> baseBodyResponse;
        baseBodyResponse = getBaseBodyError(ErrorCodes.METHOD_ARGUMENT_NOT_VALID);
        List<BaseBodyDataError> errors = new ArrayList<>();
        BindingResult bindingResult = exception.getBindingResult();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errors.add(BaseBodyDataError.builder().name(fieldError.getField())
                    .message(fieldError.getDefaultMessage()).build());
            log.info(fieldError.toString());
        }
        baseBodyResponse.getError().setErrors(errors);
        CustomException customException = new CustomException(BAD_REQUEST, baseBodyResponse);
        return handleCustomException(customException);
    }

    /**
     * Handle exception.
     *
     * @param exception the exception
     * @return the response entity
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<BaseBodyResponse> handleUnknownException(Exception exception) {
        BaseBodyResponse<?> baseBodyResponse;
        baseBodyResponse = getBaseBodyError(ErrorCodes.INTERNAL_SERVER_ERROR);
        List<BaseBodyDataError> errors = getDataErrors(exception, exception.getCause(), REQUEST_UNABLE_TO_PROCESS_MSG);
        baseBodyResponse.getError().setErrors(errors);
        CustomException customException = new CustomException(INTERNAL_SERVER_ERROR, baseBodyResponse);
        return handleCustomException(customException);
    }

    /**
     * Handle custom exception.
     *
     * @param exception the exception
     * @return the response entity
     */
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    protected ResponseEntity<BaseBodyResponse> handleCustomException(CustomException exception) {
        BaseBodyResponse baseBodyError;
        if (Objects.isNull(exception.getBaseBodyResponse())) {
            baseBodyError = getBaseBodyError(exception.getErrorCode());
        } else {
            baseBodyError = exception.getBaseBodyResponse();
        }
        List<BaseBodyDataError> errors = baseBodyError.getError().getErrors();
        if (CollectionUtils.isEmpty(errors)) {
            errors = getDataErrors(exception, exception.getCause(), REQUEST_UNABLE_TO_PROCESS_MSG);
            baseBodyError.getError().setErrors(errors);
        }
        return ResponseEntity.status(exception.getHttpStatus()).body(baseBodyError);
    }

    /**
     * build BaseBody response.
     *
     * @param errorCode the errorCode
     * @return the response entity
     */
    private BaseBodyResponse getBaseBodyError(String errorCode) {
        BaseBodyResponse baseBodyResponse = new BaseBodyResponse();
        baseBodyResponse.setResult(null);
        BaseBodyError baseBodyError = new BaseBodyError();
        baseBodyError.setMessage(this.env.getProperty(errorCode));
        baseBodyError.setErrorCode(errorCode);
        baseBodyResponse.setError(baseBodyError);
        return baseBodyResponse;
    }

    /**
     * build BaseBodyDataErrors for response.
     *
     * @param cause the cause
     * @return the List<BaseBodyDataError>
     */
    private List<BaseBodyDataError> getDataErrors(Exception ex, Throwable cause, String name) {
        List<BaseBodyDataError> errors = new ArrayList<>();
        if (Objects.nonNull(cause)) {
            errors.add(BaseBodyDataError.builder().name(cause.getClass().getName())
                    .message(cause.getMessage()).build());
        } else if (Objects.nonNull(ex)) {
            errors.add(BaseBodyDataError.builder().name(ex.getClass().getSimpleName())
                    .message(ex.getMessage()).build());
        } else {
            errors.add(BaseBodyDataError.builder()
                    .message(name).build());
        }
        return errors;
    }
}
