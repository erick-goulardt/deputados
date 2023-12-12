package com.example.api.deputados.dtos.deputado;

import com.example.api.deputados.entities.Deputy;

public record ListDeputiesResponse(
        String name, String urlPhoto, String acronymParty
) {
    public ListDeputiesResponse(Deputy deputy) {
        this(deputy.getName(), deputy.getUrlPhoto(), deputy.getAcronymParty());
    }
}
