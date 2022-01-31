package com.food.logiq.restapi.events;

import com.food.logiq.restapi.events.api.response.EventListResponse;
import com.food.logiq.restapi.events.api.response.EventResponse;
import com.food.logiq.restapi.events.api.response.MessageResponse;
import com.food.logiq.setup.Setup;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class EventControllerTest {

    @InjectMocks
    private EventController eventController;

    @Mock
    private EventService eventService;

    private Setup setup;

    @Before
    public void setUp() {
        this.setup = new Setup();
    }

    /***********************************************************************************************
     * Controller Function: createEvent()
     ***********************************************************************************************/
    @Test
    public void testCreateEvent_withValidRequest_shouldReturnMessageResponse() {
        when(eventService.createEvent(this.setup.eventRequest, this.setup.userId)).thenReturn(this.setup.messageResponseResult);
        ResponseEntity<MessageResponse> response = eventController.createEvent(this.setup.eventRequest, this.setup.userId);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getError()).isNull();
        assertThat(response.getBody().getResult()).isNotNull();
    }

    /***********************************************************************************************
     * Controller Function: GetAllEvent()
     ***********************************************************************************************/
    @Test
    public void testGetAllEvent_withValidRequest_shouldReturnEventListResponse() {
        when(eventService.getAllEvent(this.setup.userId)).thenReturn(this.setup.eventListResponseResult);
        ResponseEntity<EventListResponse> response = eventController.getAllEvent(this.setup.userId);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getError()).isNull();
        assertThat(response.getBody().getResult()).isNotNull();
    }

    /***********************************************************************************************
     * Controller Function: GetEvent()
     ***********************************************************************************************/
    @Test
    public void testGetEvent_withValidRequest_shouldReturnEventResponse() {
        when(eventService.getEvent(this.setup.eventId, this.setup.userId)).thenReturn(this.setup.eventResponseResult);
        ResponseEntity<EventResponse> response = eventController.getEvent(this.setup.eventId, this.setup.userId);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getError()).isNull();
        assertThat(response.getBody().getResult()).isNotNull();
    }

    /***********************************************************************************************
     * Controller Function: DeleteEvent()
     ***********************************************************************************************/
    @Test
    public void testDeleteEvent_withValidRequest_shouldReturnMessageResponse() {
        when(eventService.deleteEvent(this.setup.eventId, this.setup.userId)).thenReturn(this.setup.messageResponseResult);
        ResponseEntity<MessageResponse> response = eventController.deleteEvent(this.setup.eventId, this.setup.userId);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getError()).isNull();
        assertThat(response.getBody().getResult()).isNotNull();
    }
}
