package com.lexdeveloper.cineflix.repository;

import com.lexdeveloper.cineflix.entity.Episode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EpisodeRepository extends JpaRepository<Episode,Long> {
}
