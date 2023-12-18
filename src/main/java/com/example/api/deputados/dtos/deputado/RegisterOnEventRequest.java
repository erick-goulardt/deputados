package com.example.api.deputados.dtos.deputado;

import com.example.api.deputados.entities.Event;

import java.util.List;

public record RegisterOnEventRequest(
        List<Long> idDeputies,
        Long idEvent
) {
}
