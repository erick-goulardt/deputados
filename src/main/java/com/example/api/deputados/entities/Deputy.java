package com.example.api.deputados.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Deputy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String acronymParty;
    private String acronymUf;
    private Long idLegislature;
    private String urlPhoto;
    private String email;
    @ManyToMany(mappedBy = "deputies", cascade = CascadeType.ALL)
    private List<Event> events;

}
