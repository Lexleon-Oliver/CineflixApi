package com.lexdeveloper.cineflix.repository;

import com.lexdeveloper.cineflix.entity.Movie;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie,Long> {

    @Query( "SELECT m FROM Movie m WHERE m.year = ?1")
    List<Movie> findMoviesByYear(String year, Pageable pageable);

    @Query( "SELECT m FROM Movie m WHERE m.genre LIKE  %?1%")
    List<Movie> findByGenre(String genre, Pageable pageable);

    @Query( value = "SELECT * FROM public.movie order by id DESC LIMIT 25;", nativeQuery = true)
    List<Movie>  findLastMovie();

}