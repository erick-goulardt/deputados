package com.example.api.deputados.services;

import com.example.api.deputados.dtos.deputado.ListDeputiesResponse;
import com.example.api.deputados.dtos.deputado.RegisterDeputyRequest;
import com.example.api.deputados.dtos.evento.ListEventResponse;
import com.example.api.deputados.dtos.utils.LogResponse;
import com.example.api.deputados.entities.Deputy;
import com.example.api.deputados.entities.Event;
import com.example.api.deputados.repositories.DeputyRepository;
import com.example.api.deputados.repositories.EventRepository;
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
public class DeputyService {

    @Autowired
    private DeputyRepository deputyRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private ApiService apiService;

    public List<Deputy> getDeputies() {
        return deputyRepository.findAll();
    }
    public Deputy insertDeputy(Deputy deputy) {
        return deputyRepository.save(deputy);
    }

    @Transactional
    @PostConstruct
    public void saveAlLFromApi() {
        String deputyData = apiService.returnDeputados();
        List<Deputy> deputyList = convertDeputy(deputyData);
        deputyRepository.saveAll(deputyList);
    }

    public LogResponse registerDeputy(RegisterDeputyRequest registerDeputyRequest) {
        if(validateEntries(registerDeputyRequest)) {
            Deputy deputy = new Deputy();
            deputy.setEmail(registerDeputyRequest.email());
            deputy.setNome(registerDeputyRequest.name());
            deputy.setSiglaPartido(registerDeputyRequest.acronymParty());
            deputy.setSiglaUf(registerDeputyRequest.acronymUf());
            deputy.setUrlFoto(registerDeputyRequest.urlPhoto());
            deputyRepository.save(deputy);
            return new LogResponse("Cadastrado com sucesso!");
        } else {
            return new LogResponse("Falha no cadastro, entradas não validadas!");
        }
    }

    public List<ListDeputiesResponse> showAllDeputies() {
        return deputyRepository.findAll()
                .stream()
                .map(ListDeputiesResponse::new)
                .collect(Collectors.toList());
    }

    public List<ListEventResponse> showEventiesByDeputy(Long id) {
        var deputy = deputyRepository.getReferenceById(id);
        var eventsList = deputy.getEvents();
        return eventsList.stream().map(ListEventResponse::new)
                .collect(Collectors.toList());
    }

    public LogResponse associateEvent(Long idDeputy, Long idEvent) {
        var event = eventRepository.getReferenceById(idEvent);
        var deputy = deputyRepository.getReferenceById(idDeputy);
        if(deputy.getEvents().stream().anyMatch(e -> e.getId().equals(idEvent))) {
            return new LogResponse("Não é possível vincular o mesmo evento duas vezes!");
        } else {
            deputy.getEvents().add(event);
            return new LogResponse("Evento vinculado com sucesso!");
        }
    }

    private boolean validateEntries(RegisterDeputyRequest registerDeputyRequest) {
        return (!registerDeputyRequest.email().isBlank() && !registerDeputyRequest.name().isBlank()) || (!(registerDeputyRequest.name().isEmpty() | registerDeputyRequest.email().isEmpty()));
    }

    private List<Deputy> convertDeputy(String deputyData) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(deputyData);
            JsonNode dadosNode = jsonNode.get("dados");
            return objectMapper.readValue(dadosNode.toString(), new TypeReference<List<Deputy>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}

