package com.food.logiq.base.api;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Instantiates a new base body error.Annotated with
 * {@link Data @Data},{@link NoArgsConstructor @NoArgsConstructor},
 * {@link AllArgsConstructor @AllArgsConstructor},{@link Builder @Builder}
 *
 * @author Rotation5
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BaseBodyError {

    /**
     * Instance variables
     */
    String errorCode;
    String message;

    @Singular
    List<BaseBodyDataError> errors = new ArrayList<>();

    /**
     * The static Class BaseBodyDataError for the error data response object.
     * Annotated with {@link Data @Data}.
     * {@link NoArgsConstructor @NoArgsConstructor}.
     * {@link AllArgsConstructor @AllArgsConstructor}. {@link Builder @Builder}.
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class BaseBodyDataError {

        /**
         * Instance variables
         */
        String name;
        String message;
    }
}
