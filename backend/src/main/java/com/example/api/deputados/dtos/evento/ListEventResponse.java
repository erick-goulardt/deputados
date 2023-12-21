package com.example.api.deputados.dtos.evento;

import com.example.api.deputados.entities.Event;

import java.time.LocalDateTime;

public record ListEventResponse(

        Long id,
        String description,
        LocalDateTime dateInicio,
        String situation,
        String descType
) {
    public ListEventResponse (Event event){
        this(event.getId(), event.getDescricao(), event.getDataHoraInicio() ,event.getSituacao(), event.getDescricaoTipo());
    }
}