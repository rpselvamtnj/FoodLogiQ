package com.food.logiq.restapi.events;

import com.food.logiq.repositories.entity.ContentEntity;
import com.food.logiq.repositories.entity.EventEntity;
import com.food.logiq.restapi.events.api.request.Content;
import com.food.logiq.restapi.events.api.request.EventRequest;
import com.food.logiq.restapi.events.api.response.EventListResponse.EventListResponseResult;
import com.food.logiq.restapi.events.api.response.EventResponse.EventResponseResult;
import com.food.logiq.restapi.events.api.response.MessageResponse.MessageResponseResult;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper for event and content.
 *
 * @author Rotation5
 */
public class EventMapper {

    /**
     * Generate  message response result from the given parameters.
     *
     * @param eventEntity event entity
     * @param message     the after storing the event into database
     * @return the {@link MessageResponseResult @MessageResponseResult}.
     */
    public static MessageResponseResult toMessageResponse(String message, EventEntity eventEntity) {
        return MessageResponseResult.builder().message(message).eventId(eventEntity.getId()).build();
    }

    /**
     * Generate  event entity from the given parameters.
     *
     * @param eventRequest event data mode received from http request
     * @param userId       form the request
     * @return the {@link EventEntity @EventEntity}.
     */
    public static EventEntity toEventEntity(EventRequest eventRequest, String userId) {
        return EventEntity.builder()
                .type(eventRequest.getType())
                .createdBy(userId)
                .updatedBy(userId)
                .build();
    }

    /**
     * Generate  content entity from the given parameters.
     *
     * @param eventEntity event entity
     * @param content     form the request
     * @return the {@link ContentEntity @ContentEntity}.
     */
    public static ContentEntity toContentEntity(EventEntity eventEntity, Content content) {
        return ContentEntity.builder()
                .eventEntity(eventEntity)
                .gtin(content.getGtin())
                .lot(content.getLot())
                .bestByDate(content.getBestByDate())
                .expirationDate(content.getExpirationDate()).build();
    }

    /**
     * Generate  List<ContentEntity> entity from the given parameters.
     *
     * @param eventEntity event entity
     * @param contents    the list of contents from the request
     * @return the {@link List<ContentEntity> @List<ContentEntity>}.
     */
    public static List<ContentEntity> toContentEntities(EventEntity eventEntity, List<Content> contents) {
        return contents.stream()
                .map(content -> toContentEntity(eventEntity, content))
                .collect(Collectors.toList());
    }

    /**
     * Generate  EventResponseResult from the given parameters.
     *
     * @param eventEntity event entity
     * @return the {@link EventResponseResult @EventResponseResult}.
     */
    public static EventResponseResult toEventResponse(EventEntity eventEntity) {
        return EventResponseResult.builder().event(eventEntity).build();
    }

    /**
     * Generate  EventListResponseResult from the given parameters.
     *
     * @param eventEntities
     * @return the {@link EventListResponseResult @EventListResponseResult}.
     */
    public static EventListResponseResult toEventListResponse(List<EventEntity> eventEntities) {
        return EventListResponseResult.builder().events(eventEntities).build();
    }
}
