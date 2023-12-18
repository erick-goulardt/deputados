package com.example.api.deputados.dtos.deputado;

import com.example.api.deputados.entities.Deputy;

public record DetailDeputyResponse(
        String nome,
        String siglaPartido,
        String siglaUf,
        String urlFoto
) {
    public DetailDeputyResponse(Deputy deputy){
        this(deputy.getNome(), deputy.getSiglaPartido(), deputy.getSiglaUf(), deputy.getUrlFoto());
    }
}
