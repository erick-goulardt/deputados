package com.example.api.deputados.dtos.deputado;

import com.example.api.deputados.entities.Deputy;

public record ListDeputiesResponse(
        Long id, String name, String urlPhoto, String acronymParty
) {
    public ListDeputiesResponse(Deputy deputy) {
        this(deputy.getId(), deputy.getNome(), deputy.getUrlFoto(), deputy.getSiglaPartido());
    }
}
