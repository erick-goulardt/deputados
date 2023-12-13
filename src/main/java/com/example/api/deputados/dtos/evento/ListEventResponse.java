package com.example.api.deputados.dtos.evento;

import com.example.api.deputados.entities.Event;

import java.time.LocalDateTime;

public record ListEventResponse(
        LocalDateTime dataFim,
        String descType,
        LocalDateTime dataInicio
) {
    public ListEventResponse (Event event){
        this(event.getDataHoraFim(), event.getDescricaoTipo(), event.getDataHoraInicio());
    }
}