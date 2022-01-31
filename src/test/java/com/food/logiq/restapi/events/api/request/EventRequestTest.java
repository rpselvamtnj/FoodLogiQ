package com.food.logiq.restapi.events.api.request;

import com.food.logiq.setup.Setup;
import com.food.logiq.util.ValidFactory;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import java.util.Collections;
import java.util.Set;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class EventRequestTest extends ValidFactory {

    private Setup setup;

    @Before
    public void setUp() {
        this.setup = new Setup();
    }

    @Test
    public void whenRequested_withNullContents_should_returnViolation() {
        EventRequest eventRequest = this.setup.eventRequest;
        eventRequest.setContents(null);
        Set<ConstraintViolation<EventRequest>> violations = validator.validate(eventRequest);
        assertFalse(violations.isEmpty());
        assertEquals(2, violations.stream().filter(v -> v.getMessage().equals("It shouldn't be null")).count());
        assertEquals(3, violations.size());
    }

    @Test
    public void whenRequested_withEmptyContents_should_returnViolation() {
        EventRequest eventRequest = this.setup.eventRequest;
        eventRequest.setContents(Collections.emptyList());
        Set<ConstraintViolation<EventRequest>> violations = validator.validate(eventRequest);
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.stream().filter(v -> v.getMessage().equals("It shouldn't be empty")).count());
        assertEquals(2, violations.size());
    }
}
