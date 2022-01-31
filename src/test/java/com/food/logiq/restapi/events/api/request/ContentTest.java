package com.food.logiq.restapi.events.api.request;

import com.food.logiq.setup.Setup;
import com.food.logiq.util.ValidFactory;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import java.util.Set;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class ContentTest extends ValidFactory {

    private Setup setup;

    @Before
    public void setUp() {
        this.setup = new Setup();
    }

    @Test
    public void whenRequested_withValidContent_should_beAccepted() {
        Content content = this.setup.content;
        Set<ConstraintViolation<Content>> violations = validator.validate(content);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void whenRequested_withBlankGtin_should_returnViolation() {
        Content content = this.setup.content;
        content.setGtin("");
        Set<ConstraintViolation<Content>> violations = validator.validate(content);
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.stream().filter(v -> v.getMessage().equals("It shouldn't be blank")).count());
        assertEquals(1, violations.size());
    }

    @Test
    public void whenRequested_withInvalidGtin_should_returnViolation() {
        Content content = this.setup.content;
        content.setGtin("1234");
        Set<ConstraintViolation<Content>> violations = validator.validate(content);
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.stream().filter(v -> v.getMessage().equals("It Should be a 14 digit number")).count());
        assertEquals(1, violations.size());
    }

    @Test
    public void whenRequested_withBlankLot_should_returnViolation() {
        Content content = this.setup.content;
        content.setLot("");
        Set<ConstraintViolation<Content>> violations = validator.validate(content);
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.stream().filter(v -> v.getMessage().equals("It shouldn't be blank")).count());
        assertEquals(1, violations.size());
    }
}
