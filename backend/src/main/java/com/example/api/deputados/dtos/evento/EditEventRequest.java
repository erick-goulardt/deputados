package com.example.api.deputados.dtos.evento;

import java.time.LocalDateTime;

public record EditEventRequest(
        String uri,
        LocalDateTime startedDate,
        String status,
        String descType,
        String desc
) {
}
