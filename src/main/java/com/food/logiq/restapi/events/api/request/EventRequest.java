package com.food.logiq.restapi.events.api.request;

import com.food.logiq.repositories.enums.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * EventRequest data model.
 * Annotated with {@link Data @Data},{@link Builder @Builder}
 *
 * @author Rotation5
 */
@Data
public class EventRequest {

    @ApiModelProperty(example = "SHIPPING", required = true)
    @NotNull(message = "It shouldn't be null")
    private EventType type;

    @Valid
    @ApiModelProperty(required = true)
    @NotNull(message = "It shouldn't be null")
    @NotEmpty(message = "It shouldn't be empty")
    private List<Content> contents;
}
