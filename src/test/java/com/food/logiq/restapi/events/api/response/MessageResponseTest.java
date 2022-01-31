package com.food.logiq.restapi.events.api.response;

import com.food.logiq.restapi.events.api.response.MessageResponse.MessageResponseResult;
import com.food.logiq.setup.Setup;
import com.food.logiq.util.ValidFactory;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import java.util.Set;

import static org.junit.Assert.assertTrue;

public class MessageResponseTest extends ValidFactory {

    private Setup setup;

    @Before
    public void setUp() {
        this.setup = new Setup();
    }

    @Test
    public void whenResponse_withValidDetails_shouldBeAccepted() {
        Set<ConstraintViolation<MessageResponseResult>> violation = validator.validate(this.setup.messageResponseResult);
        assertTrue(violation.isEmpty());
    }
}
