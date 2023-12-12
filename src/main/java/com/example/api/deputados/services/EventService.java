package com.example.api.deputados.services;

import com.example.api.deputados.dtos.evento.ListEventResponse;
import com.example.api.deputados.dtos.evento.RegisterEventRequest;
import com.example.api.deputados.dtos.utils.LogResponse;
import com.example.api.deputados.entities.Event;
import com.example.api.deputados.repositories.DeputyRepository;
import com.example.api.deputados.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    public List<Event> saveAllFromApi(List<Event> eventList) {
        return eventRepository.saveAll(eventList);
    }
    public LogResponse createEvent(RegisterEventRequest registerEventRequest) {
        Event event = new Event(registerEventRequest);
        eventRepository.save(event);
        return new LogResponse("Evento cadastrado com sucesso!");
    }
    public List<ListEventResponse> listEvents() {
        return eventRepository.findAll().stream()
                .map(ListEventResponse::new)
                .collect(Collectors.toList());
    }
    public LogResponse deleteEvent(Long id) {
        var event = eventRepository.getReferenceById(id);
        eventRepository.delete(event);
        return new LogResponse("Evento deletado!");
    }
}
