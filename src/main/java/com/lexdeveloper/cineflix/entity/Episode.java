package com.lexdeveloper.cineflix.entity;

import lombok.*;

import jakarta.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Episode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int numEpisode;

    @Column (nullable = false, length = 100)
    private String nameEpisode;

    @Column(nullable = false)
    private String storage;

    @ManyToOne
    private Season season;
}
