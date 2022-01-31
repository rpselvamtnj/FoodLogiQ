package com.food.logiq.restapi.events.api.response;

import com.food.logiq.base.api.BaseBodyResponse;
import com.food.logiq.repositories.entity.EventEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * EventList response class.
 * Extension of {@link BaseBodyResponse} that have the {@link EventListResponseResult}.
 *
 * @author Rotation5
 */
public class EventListResponse extends BaseBodyResponse<EventListResponse.EventListResponseResult> {

    /**
     * Create a event list response result class.
     * Read only class.
     * Annotated with {@link Data @Data}, {@link NoArgsConstructor @NoArgsConstructor}, {@link Builder @Builder}
     * and {@link AllArgsConstructor @AllArgsConstructor}.
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class EventListResponseResult {

        private List<EventEntity> events;
    }
}
