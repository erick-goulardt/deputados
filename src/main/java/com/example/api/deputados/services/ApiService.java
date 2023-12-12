package com.example.api.deputados.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiService {
    private final static String URL_DEPUTY = "https://dadosabertos.camara.leg.br/api/v2/deputados?ordem=ASC&ordenarPor=nome";
    private final static String URL_EVENT = "https://dadosabertos.camara.leg.br/api/v2/eventos?ordem=ASC&ordenarPor=dataHoraInicio";
    private RestTemplate restTemplate;
    public String returnDeputados() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(URL_DEPUTY, String.class);
        return responseEntity.getBody();
    }
    public String returnEventos() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(URL_EVENT, String.class);
        return responseEntity.getBody();
    }
}
