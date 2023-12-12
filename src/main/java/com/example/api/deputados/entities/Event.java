package com.example.api.deputados.entities;

import com.example.api.deputados.dtos.evento.RegisterEventRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uri;
    private LocalDateTime startedDate;
    private String status;
    private String descType;
    private String description;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "deputy_event",
            joinColumns = @JoinColumn(name = "deputy_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id"))
    private List<Deputy> deputies;

    public Event(RegisterEventRequest eventRequest){
        this.description = eventRequest.desc();
        this.startedDate = eventRequest.startedDate();
        this.status = eventRequest.status();
        this.descType = eventRequest.descType();
        this.uri = eventRequest.uri();
    }

}