package com.example.api.deputados.repositories;

import com.example.api.deputados.entities.Deputy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DeputyRepository extends JpaRepository<Deputy, Long> {
    Optional<Deputy> findDeputyByEmail(String email);
    Optional<List<Deputy>> findDeputiesByAcronymParty(String acronymPart);
}
