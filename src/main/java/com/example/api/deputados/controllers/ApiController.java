package com.example.api.deputados.controllers;

import com.example.api.deputados.entities.Deputy;
import com.example.api.deputados.entities.Event;
import com.example.api.deputados.services.ApiService;
import com.example.api.deputados.services.DeputyService;
import com.example.api.deputados.services.EventService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Collections;
import java.util.List;


@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private ApiService apiService;
    @Autowired
    private DeputyService depService;
    @Autowired
    private EventService eventosService;

    @GetMapping("/sync-deputados")
    public ResponseEntity<List<Deputy>> syncDeputados() {
        String deputyData = apiService.returnDeputados();
        List<Deputy> deputyList = convertDeputies(deputyData);
        depService.saveAlLFromApi(deputyList);
        return ResponseEntity.status(200).body(deputyList);
    }

    @GetMapping("/sync-eventos")
    public ResponseEntity<List<Event>> syncEventos() {
        String eventsData = apiService.returnEventos();
        List<Event> eventList = convertEvents(eventsData);
        eventosService.saveAllFromApi(eventList);
        return ResponseEntity.status(200).body(eventList);
    }

    private List<Deputy> convertDeputies(String deputyData) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(deputyData, new TypeReference<List<Deputy>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
    private List<Event> convertEvents(String eventsData) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(eventsData, new TypeReference<List<Event>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

}
