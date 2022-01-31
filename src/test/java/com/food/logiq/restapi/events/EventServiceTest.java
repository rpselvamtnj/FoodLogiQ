package com.food.logiq.restapi.events;

import com.food.logiq.repositories.ContentRepository;
import com.food.logiq.repositories.EventRepository;
import com.food.logiq.restapi.events.api.response.EventListResponse.EventListResponseResult;
import com.food.logiq.restapi.events.api.response.EventResponse.EventResponseResult;
import com.food.logiq.restapi.events.api.response.MessageResponse.MessageResponseResult;
import com.food.logiq.setup.Setup;
import com.food.logiq.util.exception.ConflictException;
import com.food.logiq.util.exception.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EventServiceTest {

    @InjectMocks
    EventService eventService;

    @Mock
    EventRepository eventRepository;

    @Mock
    ContentRepository contentRepository;

    private Setup setup;

    @Before
    public void setUp() {
        this.setup = new Setup();
    }

    /***********************************************************************************************
     * Service Function: createEvent()
     ***********************************************************************************************/

    @Test
    public void testCreateEvent_withValidRequest_shouldReturnMessageResponse() {
        when(eventRepository.save(any())).thenReturn(setup.eventEntity);
        when(contentRepository.saveAll(any())).thenReturn(null);
        MessageResponseResult messageResponseResult = eventService.createEvent(this.setup.eventRequest, this.setup.userId);
        assertThat(messageResponseResult).isNotNull();
        assertThat(messageResponseResult).hasFieldOrProperty("eventId");
    }

    @Test(expected = ConflictException.class)
    public void testCreateEvent_withInValidGtinLot_shouldReturnMessageResponse() {
        when(eventRepository.save(any())).thenReturn(setup.eventEntity);
        when(contentRepository.saveAll(any())).thenThrow(ConflictException.class);
        ;
        eventService.createEvent(this.setup.eventRequest, this.setup.userId);
    }

    /***********************************************************************************************
     * Service Function: GetAllEvent()
     ***********************************************************************************************/
    @Test
    public void testGetAllEvents_withValidRequest_shouldReturnMessageResponse() {
        when(eventRepository.findEventEntityByCreatedByAndIsDeletedOrderByCreatedAtDesc(this.setup.userId, false))
                .thenReturn(Arrays.asList(this.setup.eventEntity));
        EventListResponseResult eventListResponseResult = eventService.getAllEvent(this.setup.userId);
        assertThat(eventListResponseResult).isNotNull();
        assertThat(eventListResponseResult.getEvents()).isNotEmpty();
    }

    /***********************************************************************************************
     * Service Function: GetEvent()
     ***********************************************************************************************/
    @Test
    public void testGetEvents_withValidRequest_shouldReturnMessageResponse() {
        when(eventRepository.findEventEntityByIdAndCreatedByAndIsDeleted(this.setup.eventId, this.setup.userId, false))
                .thenReturn(Optional.of(this.setup.eventEntity));
        EventResponseResult eventResponseResult = eventService.getEvent(this.setup.eventId, this.setup.userId);
        assertThat(eventResponseResult).isNotNull();
        assertThat(eventResponseResult.getEvent()).isNotNull();
    }

    @Test(expected = NotFoundException.class)
    public void testGetEvent_withInValidRequest_shouldThrowNotFoundException() {
        when(eventRepository.findEventEntityByIdAndCreatedByAndIsDeleted(this.setup.eventId, this.setup.userId, false))
                .thenReturn(Optional.empty());
        eventService.getEvent(this.setup.eventId, this.setup.userId);
    }
}
