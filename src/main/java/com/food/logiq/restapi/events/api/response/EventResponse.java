package com.food.logiq.restapi.events.api.response;

import com.food.logiq.base.api.BaseBodyResponse;
import com.food.logiq.repositories.entity.EventEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Event response class.
 * Extension of {@link BaseBodyResponse} that have the {@link EventResponse.EventResponseResult}.
 *
 * @author Rotation5
 */
public class EventResponse extends BaseBodyResponse<EventResponse.EventResponseResult> {

    /**
     * Create a event response result class.
     * Read only class.
     * Annotated with {@link Data @Data}, {@link NoArgsConstructor @NoArgsConstructor}, {@link Builder @Builder}
     * and {@link AllArgsConstructor @AllArgsConstructor}.
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class EventResponseResult {

        private EventEntity event;
    }
}
