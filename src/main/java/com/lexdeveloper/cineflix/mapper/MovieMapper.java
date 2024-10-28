package com.lexdeveloper.cineflix.mapper;

import com.lexdeveloper.cineflix.dto.request.MovieDTO;
import com.lexdeveloper.cineflix.entity.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MovieMapper {

    MovieMapper INSTANCE = Mappers.getMapper(MovieMapper.class);

    Movie toModel(MovieDTO movieDTO);
    MovieDTO toDTO (Movie movie);
}
