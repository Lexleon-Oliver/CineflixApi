package com.lexdeveloper.cineflix.entity;

import lombok.*;

import jakarta.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Serie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long tmdb;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String nameBr;

    @Column(nullable = false)
    private String year;

    @Column(nullable = false, length = 700)
    private String description;

    @Column(nullable = false)
    private String thumbnail;

    @Column(nullable = false)
    private String background;

    @Column(nullable = false)
    private String genre;

    @Column(nullable = false)
    private Integer rating;

    @Column()
    private Integer minAge;

    @Column()
    private Integer time;

    @Column()
    private boolean completed;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "serie")
    private List<Season> seasons;

}
