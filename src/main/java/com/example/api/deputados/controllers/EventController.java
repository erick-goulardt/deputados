package com.example.api.deputados.controllers;

import com.example.api.deputados.dtos.evento.ListEventResponse;
import com.example.api.deputados.dtos.evento.RegisterEventRequest;
import com.example.api.deputados.dtos.utils.LogResponse;
import com.example.api.deputados.services.EventService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/eventos")
@RestController
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping("/registrar")
    @Transactional
    public ResponseEntity<LogResponse> registerEvent(RegisterEventRequest eventRequest) {
        var response = eventService.createEvent(eventRequest);
        return ResponseEntity.ok().body(response);
    }
    @GetMapping
    public ResponseEntity<List<ListEventResponse>> listEvents() {
        var response = eventService.listEvents();
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<LogResponse> deleteEvent(@PathVariable Long id) {
        var response = eventService.deleteEvent(id);
        return ResponseEntity.ok().body(response);
    }
}
