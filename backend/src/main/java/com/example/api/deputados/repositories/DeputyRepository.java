package com.example.api.deputados.repositories;

import com.example.api.deputados.dtos.evento.ListEventResponse;
import com.example.api.deputados.entities.Deputy;
import com.example.api.deputados.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DeputyRepository extends JpaRepository<Deputy, Long> {
    Optional<Deputy> findDeputyByEmail(String email);
    Optional<List<Deputy>> findDeputiesBySiglaPartido(String siglaPartido);

    @Query("SELECT d FROM Deputy d JOIN d.events e WHERE e.id = :eventoId")
    List<Deputy> findDeputadosByEventId(@Param("eventoId") Long eventoId);
}
