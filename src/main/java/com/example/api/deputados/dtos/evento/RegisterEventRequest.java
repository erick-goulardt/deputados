package com.example.api.deputados.dtos.evento;

import com.example.api.deputados.entities.Event;

import java.time.LocalDateTime;

public record RegisterEventRequest(
        String desc,
        LocalDateTime startedDate,
        String descType,
        String uri,
        String status

) {
    public RegisterEventRequest(Event event){
        this(event.getDescription(), event.getStartedDate(), event.getDescType(), event.getUri(), event.getStatus());
    }
}
