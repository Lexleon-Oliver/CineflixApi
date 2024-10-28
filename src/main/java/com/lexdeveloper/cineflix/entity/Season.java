package com.lexdeveloper.cineflix.entity;

import lombok.*;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Season {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int numSeason;

    @Column()
    private String seasonBack;

    @ManyToOne
    private Serie serie;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "season")
    private List<Episode> episodes;

}
