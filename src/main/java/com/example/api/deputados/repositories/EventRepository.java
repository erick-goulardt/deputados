package com.example.api.deputados.repositories;

import com.example.api.deputados.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {

}
