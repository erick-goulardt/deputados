package com.example.api.deputados.services;

import com.example.api.deputados.dtos.deputado.ListDeputiesResponse;
import com.example.api.deputados.dtos.deputado.RegisterDeputyRequest;
import com.example.api.deputados.dtos.evento.ListEventResponse;
import com.example.api.deputados.dtos.utils.LogResponse;
import com.example.api.deputados.entities.Deputy;
import com.example.api.deputados.repositories.DeputyRepository;
import com.example.api.deputados.repositories.EventRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeputyService {

    @Autowired
    private DeputyRepository deputyRepository;
    @Autowired
    private EventRepository eventRepository;

    public List<Deputy> getDeputies() {
        return deputyRepository.findAll();
    }
    public Deputy insertDeputy(Deputy deputy) {
        return deputyRepository.save(deputy);
    }

    public List<Deputy> saveAlLFromApi(List<Deputy> deputyList) {
        return deputyRepository.saveAll(deputyList);
    }

    public LogResponse registerDeputy(RegisterDeputyRequest registerDeputyRequest) {
        if(validateEntries(registerDeputyRequest)) {
            Deputy deputy = new Deputy();
            deputy.setEmail(registerDeputyRequest.email());
            deputy.setName(registerDeputyRequest.name());
            deputy.setAcronymParty(registerDeputyRequest.acronymParty());
            deputy.setAcronymUf(registerDeputyRequest.acronymUf());
            deputy.setUrlPhoto(registerDeputyRequest.urlPhoto());
            deputy.setIdLegislature(registerDeputyRequest.idLegislature());
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
}

