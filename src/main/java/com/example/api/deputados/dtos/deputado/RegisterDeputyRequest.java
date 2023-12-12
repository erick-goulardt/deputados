package com.example.api.deputados.dtos.deputado;

public record RegisterDeputyRequest(
        String name,
        String acronymParty,
        String acronymUf,
        Long idLegislature,
        String urlPhoto,
        String email
) {
}
