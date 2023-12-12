package com.example.api.deputados.repositories;

import com.example.api.deputados.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {

}
