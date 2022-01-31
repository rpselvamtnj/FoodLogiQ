package com.food.logiq.setup;

import com.food.logiq.repositories.entity.ContentEntity;
import com.food.logiq.repositories.entity.EventEntity;
import com.food.logiq.restapi.events.api.request.Content;
import com.food.logiq.restapi.events.api.request.EventRequest;
import com.food.logiq.restapi.events.api.response.EventListResponse.EventListResponseResult;
import com.food.logiq.restapi.events.api.response.EventResponse.EventResponseResult;
import com.food.logiq.restapi.events.api.response.MessageResponse.MessageResponseResult;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

public class Setup {

    public EventRequest eventRequest;
    public EventListResponseResult eventListResponseResult;
    public EventResponseResult eventResponseResult;
    public MessageResponseResult messageResponseResult;
    public Content content;
    public ContentEntity contentEntity;
    public EventEntity eventEntity;
    public LocalDate date = LocalDate.now();
    public String userId = "12345";
    public Long eventId = 1L;

    public Setup() {
        this.content = new Content();
        this.content.setGtin("12345678901234");
        this.content.setLot("axdfc");
        this.content.setBestByDate(date);
        this.content.setExpirationDate(date);
        ArrayList<Content> contents = new ArrayList<>();
        contents.add(this.content);
        this.eventRequest = new EventRequest();
        this.eventRequest.setContents(contents);

        this.eventListResponseResult = EventListResponseResult.builder().events(Collections.emptyList()).build();

        this.eventEntity = EventEntity.builder().id(1L).createdBy(userId).isDeleted(true).build();
        this.contentEntity = ContentEntity.builder().gtin("12345678901234").lot("axdfc")
                .bestByDate(date).expirationDate(date).eventEntity(eventEntity).build();

        this.eventResponseResult = EventResponseResult.builder().event(eventEntity).build();
        this.messageResponseResult = MessageResponseResult.builder().eventId(1L).message("Created").build();

    }
}
