package com.food.logiq.restapi.events;

import com.food.logiq.restapi.events.api.request.EventRequest;
import com.food.logiq.restapi.events.api.response.EventListResponse;
import com.food.logiq.restapi.events.api.response.EventResponse;
import com.food.logiq.restapi.events.api.response.MessageResponse;
import com.food.logiq.util.exception.NotFoundException;
import com.food.logiq.util.exception.UnauthorizedException;
import com.food.logiq.util.Constants;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static com.food.logiq.base.api.BaseBodyResponse.result;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Controller for managing events and contents (Create, List, get, and Delete API).
 * Annotated with {@link RestController @RestController}, {@link RequestMapping @RequestMapping}, and {@link Slf4j @Slf4j}.
 *
 * @author Rotation5
 */
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success"),
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 403, message = "Forbidden", response = RuntimeException.class),
        @ApiResponse(code = 401, message = "Unauthorized", response = UnauthorizedException.class),
        @ApiResponse(code = 415, message = "UnSupported Media Type", response = HttpClientErrorException.UnsupportedMediaType.class),
        @ApiResponse(code = 404, message = "Not found", response = NotFoundException.class),
        @ApiResponse(code = 429, message = "Too Many Request", response = RuntimeException.class),
})
@Slf4j
@RestController
@RequestMapping(value = "api/v1/events")
@Validated
public class EventController {

    /**
     * Instance variables
     */
    private final EventService eventService;

    /**
     * Create a new {@code EventController} with the given parameters class instance.
     *
     * @param eventService the {@link EventService @EventService} class instance
     * @URL api/v1/events
     */
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    /**
     * Create a new event with the request body.
     * Annotated with {@link ApiOperation @ApiOperation} and {@link PostMapping @PostMapping}.
     *
     * @param request the {@link EventRequest @EventRequest}.
     * @param userId  the {@link String @String}.
     * @return the {@link ResponseEntity<MessageResponse> @ResponseEntity<MessageResponse>} as response entity.
     * @URL api/v1/events
     */
    @ApiOperation(value = "Create Event", notes = "Create Event based on the request")
    @ApiResponses({
            @ApiResponse(code = 200, message = Constants.EVENT_CREATED, response = MessageResponse.class)
    })
    @PostMapping(name = "CreateEvent", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageResponse> createEvent(@Valid @RequestBody EventRequest request,
                                                       @ApiParam(hidden = true) @Valid @RequestHeader(value = "userId") @NotBlank String userId) {
        log.info("Event Create API initiated... Request: {},{}", request.toString(), userId);
        MessageResponse response = result(this.eventService.createEvent(request, userId), MessageResponse.class);
        log.info("Event Create API finished...");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Get specific event  based on the event and userId.
     * Annotated with {@link ApiOperation @ApiOperation} and {@link GetMapping @GetMapping}.
     *
     * @param eventId the {@link Long @Long}.
     * @param userId  the {@link String @String}.
     * @return the {@link ResponseEntity<EventResponse> @ResponseEntity<EventResponse>} as response entity.
     * @URL api/v1/events/1
     */
    @ApiOperation(value = "Get Event", notes = "Get Event based on the given userId and eventId")
    @GetMapping(name = "GetEvent", value = "/{eventId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<EventResponse> getEvent(@Valid @PathVariable("eventId") @NotNull Long eventId,
                                                  @ApiParam(hidden = true) @Valid @RequestHeader(value = "userId") @NotBlank String userId) {
        log.info("Get Event API initiated... Request: {},{}", eventId, userId);
        EventResponse response = result(this.eventService.getEvent(eventId, userId), EventResponse.class);
        log.info("Get Event API finished...");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Get all events based on the userId.
     * Annotated with {@link ApiOperation @ApiOperation} and {@link GetMapping @GetMapping}.
     *
     * @param userId the {@link String @String}.
     * @return the {@link ResponseEntity<EventListResponse> @ResponseEntity<EventListResponse>} as response entity.
     * @URL api/v1/events
     */
    @ApiOperation(value = "Get All Event", notes = "Get All Event based on the given userId")
    @GetMapping(name = "GetAllEvent", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<EventListResponse> getAllEvent(@ApiParam(hidden = true) @Valid @RequestHeader(value = "userId") @NotBlank String userId) {
        log.info("Get All Event API initiated... Request: {}", userId);
        EventListResponse response = result(this.eventService.getAllEvent(userId), EventListResponse.class);
        log.info("Get All Event API finished...");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Delete specific event based on the given eventId and userId.
     * Annotated with {@link ApiOperation @ApiOperation} and {@link DeleteMapping @DeleteMapping}.
     *
     * @param eventId the {@link Long @Long}.
     * @param userId  the {@link String @String}.
     * @return the {@link ResponseEntity<MessageResponse> @ResponseEntity<MessageResponse>} as response entity.
     * @URL api/v1/events/1
     */
    @ApiOperation(value = "Delete Event", notes = "Delete Event event based on the given eventId and userId")
    @DeleteMapping(name = "DeleteEvent", value = "/{eventId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageResponse> deleteEvent(@Valid @PathVariable("eventId") @NotNull Long eventId,
                                                       @ApiParam(hidden = true) @Valid @RequestHeader(value = "userId") @NotBlank String userId) {
        log.info("Delete Event API initiated... Request: {},{}", eventId, userId);
        MessageResponse response = result(this.eventService.deleteEvent(eventId, userId), MessageResponse.class);
        log.info("Delete Event API finished...");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
