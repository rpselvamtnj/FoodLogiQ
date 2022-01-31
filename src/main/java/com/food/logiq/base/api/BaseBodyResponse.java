package com.food.logiq.base.api;

import java.lang.reflect.InvocationTargetException;

import lombok.Data;

/**
 * Instantiates a new base body response.Annotated with
 * {@link Data @Data}.
 *
 * @author Rotation5
 */
@Data
public class BaseBodyResponse<T> {

    /**
     * The Constant DATE_TIME_FORMAT_PATTERN.
     */
    public static final String DATE_TIME_FORMAT_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSX";

    /**
     * The Constant LOCAL_DATE_TIME_FORMAT_PATTERN.
     */
    public static final String LOCAL_DATE_TIME_FORMAT_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";

    /**
     * The Constant LOCAL_DATE_FORMAT_PATTERN.
     */
    public static final String LOCAL_DATE_FORMAT_PATTERN = "yyyy-MM-dd";

    /**
     * The result.
     */
    protected T result;

    /**
     * The error.
     */
    protected BaseBodyError error;

    /**
     * Checks for result.
     *
     * @return true, if successful
     */
    public boolean hasResult() {
        return this.result != null;
    }

    /**
     * Checks for error.
     *
     * @return true, if successful
     */
    public boolean hasError() {
        return this.error != null;
    }

    /**
     * Result.
     *
     * @param <T>    the generic type
     * @param result the result
     * @return the base body response
     */
    public static <T> BaseBodyResponse<T> result(T result) {
        BaseBodyResponse<T> response = new BaseBodyResponse<>();
        response.setResult(result);
        return response;
    }

    /**
     * Result.
     *
     * @param <T>    the generic type
     * @param <R>    the generic type
     * @param result the result
     * @param clazz  the clazz
     * @return the r
     */
    public static <T, R extends BaseBodyResponse<T>> R result(T result, Class<R> clazz) {
        try {
            R response = clazz.getDeclaredConstructor().newInstance();
            response.setResult(result);
            return response;
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Error.
     *
     * @param <T>   the generic type
     * @param error the error
     * @return the base body response
     */
    public static <T> BaseBodyResponse<T> error(BaseBodyError error) {
        BaseBodyResponse<T> response = new BaseBodyResponse<>();
        response.setError(error);
        return response;
    }

    /**
     * Error.
     *
     * @param <R>   the generic type
     * @param error the error
     * @param clazz the clazz
     * @return the r
     */
    public static <R extends BaseBodyResponse<?>> R error(BaseBodyError error, Class<R> clazz) {
        try {
            R response = clazz.getDeclaredConstructor().newInstance();
            response.setError(error);
            return response;
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
            throw new RuntimeException(e);
        }
    }
}
