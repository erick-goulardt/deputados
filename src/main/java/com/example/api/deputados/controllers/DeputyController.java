package com.example.api.deputados.controllers;

import com.example.api.deputados.dtos.deputado.ListDeputiesResponse;
import com.example.api.deputados.dtos.deputado.RegisterDeputyRequest;
import com.example.api.deputados.dtos.deputado.RegisterOnEventRequest;
import com.example.api.deputados.dtos.evento.ListEventResponse;
import com.example.api.deputados.dtos.utils.LogResponse;
import com.example.api.deputados.services.DeputyService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/deputados")
@RestController
public class DeputyController {
    @Autowired
    private DeputyService deputyService;

    @PostMapping("/cadastro")
    @Transactional
    public ResponseEntity<LogResponse> registerDeputy(@RequestBody RegisterDeputyRequest registerDeputyRequest) {
        var response = deputyService.registerDeputy(registerDeputyRequest);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    public ResponseEntity<List<ListDeputiesResponse>> listDeputies() {
        var response = deputyService.showAllDeputies();
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/define-evento")
    @Transactional
    public ResponseEntity<LogResponse> selectAnEvent(@RequestBody RegisterOnEventRequest registerOnEventRequest) {
        var response = deputyService.associateEvent(registerOnEventRequest);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/evento/{id}")
    public ResponseEntity<List<ListDeputiesResponse>> showDeputiesOnEvent(@PathVariable Long id) {
        var response = deputyService.showDeputiesOnEvent(id);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<LogResponse> deleteDeputy(@PathVariable Long id) {
        var response = deputyService.deleteDeputy(id);
        return ResponseEntity.ok().body(response);
    }

}

