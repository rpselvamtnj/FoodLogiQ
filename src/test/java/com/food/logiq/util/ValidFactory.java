package com.food.logiq.util;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ValidFactory<T> {

    protected static Validator validator;

    private static ValidatorFactory validatorFactory;

    @BeforeClass
    public static void createValidator() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @AfterClass
    public static void close() {
        validatorFactory.close();
    }

    protected void assertError(Set<ConstraintViolation<T>> violations, String message, long size) {
        assertFalse(violations.isEmpty());
        assertEquals(size, violations.size());
        assertThat(violations.stream().filter(msg -> msg.getMessage().equals(message)).count(), is(1L));
    }

    protected void assertSuccess(Set<ConstraintViolation<T>> violations) {
        assertTrue(violations.isEmpty());
        assertEquals(0, violations.size());
    }

    protected void assertNotNull(Set<ConstraintViolation<T>> violations) {
        this.assertError(violations, "must not be null", 1L);
    }

    protected void assertNotBlank(Set<ConstraintViolation<T>> violations) {
        this.assertError(violations, "must not be blank", 1L);
    }

    protected void assertNotEmpty(Set<ConstraintViolation<T>> violations) {
        this.assertError(violations, "must not be empty", 1L);
    }

    protected void assertNotEmpty(Set<ConstraintViolation<T>> violations, long size) {
        this.assertError(violations, "must not be empty", size);
    }

    protected void assertEmail(Set<ConstraintViolation<T>> violations) {
        this.assertError(violations, "must be a well-formed email address", 1L);
    }
}