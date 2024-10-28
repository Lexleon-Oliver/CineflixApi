package com.lexdeveloper.cineflix.mapper;

import com.lexdeveloper.cineflix.dto.request.MovieDTO;
import com.lexdeveloper.cineflix.entity.Movie;

public class MovieMapperImpl implements MovieMapper{
    @Override
    public Movie toModel(MovieDTO movieDTO) {
        if ( movieDTO == null ) {
            return null;
        }

        Movie movie = new Movie();

        movie.setId( movieDTO.getId() );
        movie.setTmdb(movieDTO.getTmdb());
        movie.setName( movieDTO.getName() );
        movie.setNameBr( movieDTO.getNameBr() );
        movie.setYear( movieDTO.getYear() );
        movie.setDescription( movieDTO.getDescription() );
        movie.setThumbnail( movieDTO.getThumbnail() );
        movie.setBackground( movieDTO.getBackground() );
        movie.setStorage( movieDTO.getStorage() );
        movie.setGenre( movieDTO.getGenre() );
        movie.setRating( movieDTO.getRating() );
        movie.setMinAge( movieDTO.getMinAge() );
        movie.setTime( movieDTO.getTime() );

        return movie;
    }

    @Override
    public MovieDTO toDTO(Movie movie) {
        if ( movie == null ) {
            return null;
        }

        MovieDTO.MovieDTOBuilder movieDTO = MovieDTO.builder();

        movieDTO.id( movie.getId() );
        movieDTO.tmdb( movie.getTmdb() );
        movieDTO.name( movie.getName() );
        movieDTO.nameBr( movie.getNameBr() );
        movieDTO.year( movie.getYear() );
        movieDTO.description( movie.getDescription() );
        movieDTO.thumbnail( movie.getThumbnail() );
        movieDTO.background( movie.getBackground() );
        movieDTO.storage( movie.getStorage() );
        movieDTO.genre( movie.getGenre() );
        movieDTO.rating( movie.getRating() );
        movieDTO.time( movie.getTime() );
        movieDTO.minAge( movie.getMinAge() );

        return movieDTO.build();
    }
}
