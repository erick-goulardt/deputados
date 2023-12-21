package com.example.api.deputados.entities;

import com.example.api.deputados.dtos.evento.RegisterEventRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
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
@JsonIgnoreProperties(ignoreUnknown = true)
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime dataHoraInicio;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime dataHoraFim;
    private String situacao;
    private String descricaoTipo;
    @Column(columnDefinition = "LONGTEXT")
    private String descricao;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "deputy_event",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "deputy_id"))
    private List<Deputy> deputies;

    public Event(RegisterEventRequest eventRequest){
        this.dataHoraInicio = eventRequest.startedDate();
        this.descricao = eventRequest.description();
        this.dataHoraFim = eventRequest.endedDate();
        this.situacao = eventRequest.status();
        this.descricaoTipo = eventRequest.descType();
    }

}