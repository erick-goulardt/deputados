package com.example.api.deputados.services;

import com.example.api.deputados.dtos.evento.ListEventResponse;
import com.example.api.deputados.dtos.evento.RegisterEventRequest;
import com.example.api.deputados.dtos.utils.LogResponse;
import com.example.api.deputados.entities.Event;
import com.example.api.deputados.repositories.EventRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private ApiService apiService;

    @PostConstruct
    @Transactional
    public void saveAllFromApi() {
        String eventData = apiService.returnEventos();
        List<Event> eventList = convertEvents(eventData);
        eventRepository.saveAll(eventList);
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

    private List<Event> convertEvents(String deputyData) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(deputyData);
            JsonNode dadosNode = jsonNode.get("dados");
            return objectMapper.readValue(dadosNode.toString(), new TypeReference<List<Event>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
