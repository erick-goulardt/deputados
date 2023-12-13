package com.example.api.deputados.dtos.evento;

import com.example.api.deputados.entities.Event;

import java.time.LocalDateTime;

public record RegisterEventRequest(
        LocalDateTime startedDate,
        LocalDateTime endedDate,
        String status,
        String descType
) {
    public RegisterEventRequest(Event event){
        this(event.getDataHoraInicio(), event.getDataHoraFim(), event.getSituacao(), event.getDescricaoTipo());
    }
}
