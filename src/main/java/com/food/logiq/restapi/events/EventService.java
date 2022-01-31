package com.food.logiq.restapi.events;

import com.food.logiq.repositories.entity.ContentEntity;
import com.food.logiq.repositories.entity.EventEntity;
import com.food.logiq.restapi.events.api.request.EventRequest;
import com.food.logiq.restapi.events.api.response.EventListResponse.EventListResponseResult;
import com.food.logiq.restapi.events.api.response.EventResponse.EventResponseResult;
import com.food.logiq.restapi.events.api.response.MessageResponse.MessageResponseResult;
import com.food.logiq.util.exception.ConflictException;
import com.food.logiq.util.exception.NotFoundException;
import com.food.logiq.repositories.ContentRepository;
import com.food.logiq.repositories.EventRepository;
import com.food.logiq.util.Constants;
import com.food.logiq.util.ErrorCodes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service for managing events.
 * Annotated with {@link Service @Service}, and {@link Slf4j @Slf4j}.
 *
 * @author Rotation5
 * @see EventController
 */
@Slf4j
@Service
public class EventService {

    /**
     * Instance variables
     */
    private final EventRepository eventRepository;
    private final ContentRepository contentRepository;

    /**
     * Create a new {@code EventService} with the given parameters class instance.
     *
     * @param eventRepository   the {@link EventRepository @EventRepository} class instance
     * @param contentRepository the {@link ContentRepository @ContentRepository} class instance
     */
    public EventService(EventRepository eventRepository, ContentRepository contentRepository) {
        this.eventRepository = eventRepository;
        this.contentRepository = contentRepository;
    }

    /**
     * Save the event.
     *
     * @param request the {@link EventRequest @EventRequest} data model.
     * @param userId  the {@link String @String}.
     * @return the {@link MessageResponseResult @MessageResponseResult}.
     */
    @Transactional
    public MessageResponseResult createEvent(EventRequest request, String userId) {

        EventEntity eventEntity = EventMapper.toEventEntity(request, userId);
        eventRepository.save(eventEntity);
        log.info("Event saved");
        List<ContentEntity> contentEntityList = EventMapper.toContentEntities(eventEntity, request.getContents());
        try {
            contentRepository.saveAll(contentEntityList);
            log.info("Event contents saved");
        } catch (DataIntegrityViolationException exception) {
            throw new ConflictException(ErrorCodes.GTIN_LOT_CONFLICT, exception.getMostSpecificCause());
        }

        return EventMapper.toMessageResponse(Constants.EVENT_CREATED, eventEntity);
    }

    /**
     * Get all the event based on the userId.
     *
     * @param userId the {@link String @String}.
     * @return the {@link EventListResponseResult @EventListResponseResult}.
     */
    public EventListResponseResult getAllEvent(String userId) {
        List<EventEntity> eventEntities = eventRepository.findEventEntityByCreatedByAndIsDeletedOrderByCreatedAtDesc(
                userId, false);
        log.info("Events retrieved from table {}", eventEntities.size());
        return EventMapper.toEventListResponse(eventEntities);
    }

    /**
     * Get specific event based on the eventId and userId.
     *
     * @param eventId the {@link Long @Long}.
     * @param userId  the {@link String @String}.
     * @return the {@link EventListResponseResult @EventListResponseResult}.
     */
    public EventResponseResult getEvent(long eventId, String userId) {
        Optional<EventEntity> eventEntityOptional = eventRepository.findEventEntityByIdAndCreatedByAndIsDeleted(eventId,
                userId, false);

        if (!eventEntityOptional.isPresent()) {
            throw new NotFoundException(ErrorCodes.EVENT_NOT_FOUND);
        }
        log.info("Event retrieved from table {}", eventEntityOptional.get());
        return EventMapper.toEventResponse(eventEntityOptional.get());
    }

    /**
     * Delete specific event based on the eventId and userId.
     *
     * @param eventId the {@link Long @Long}.
     * @param userId  the {@link String @String}.
     * @return the {@link MessageResponseResult @MessageResponseResult}.
     */
    public MessageResponseResult deleteEvent(long eventId, String userId) {
        log.info("Fetch event from db {},{}", eventId, userId);
        Optional<EventEntity> eventEntityOptional = eventRepository.findEventEntityByIdAndCreatedBy(eventId, userId);

        if (!eventEntityOptional.isPresent()) {
            throw new NotFoundException(ErrorCodes.EVENT_NOT_FOUND);
        }
        EventEntity eventEntity = eventEntityOptional.get();
        eventRepository.deleteEventByEventId(eventEntity.getId());
        log.info("Event deleted {}", eventEntity);
        return EventMapper.toMessageResponse(Constants.EVENT_DELETED, eventEntity);
    }
}
