package com.food.logiq.restapi.events.api.response;

import com.food.logiq.base.api.BaseBodyResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Message response class.
 * Extension of {@link BaseBodyResponse} that have the {@link MessageResponse.MessageResponseResult}.
 *
 * @author Rotation5
 */
public class MessageResponse extends BaseBodyResponse<MessageResponse.MessageResponseResult> {

    /**
     * Create a message response result class.
     * Read only class.
     * Annotated with {@link Data @Data}, {@link NoArgsConstructor @NoArgsConstructor}, {@link Builder @Builder}
     * and {@link AllArgsConstructor @AllArgsConstructor}.
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class MessageResponseResult {

        private Long eventId;
        private String message;
    }
}
