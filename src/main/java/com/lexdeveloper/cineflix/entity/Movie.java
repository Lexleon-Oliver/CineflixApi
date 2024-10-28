package com.lexdeveloper.cineflix.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Movie {

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
    private String storage;

    @Column(nullable = false)
    private String genre;

    @Column(nullable = false)
    private Integer rating;

    @Column()
    private Integer minAge;

    @Column()
    private Integer time;
}