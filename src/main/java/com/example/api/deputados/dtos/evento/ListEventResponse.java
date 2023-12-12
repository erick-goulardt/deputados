package com.example.api.deputados.dtos.evento;

import com.example.api.deputados.entities.Event;

import java.time.LocalDateTime;

public record ListEventResponse(
        String desc,
        String descType,
        LocalDateTime data
) {
    public ListEventResponse (Event event){
        this(event.getDescription(), event.getDescType(), event.getStartedDate());
    }
}