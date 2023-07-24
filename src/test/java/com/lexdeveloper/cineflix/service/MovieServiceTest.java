package com.lexdeveloper.cineflix.service;

import com.lexdeveloper.cineflix.builder.MovieDtoBuilder;
import com.lexdeveloper.cineflix.dto.request.MovieDTO;
import com.lexdeveloper.cineflix.dto.response.MessageResponseDTO;
import com.lexdeveloper.cineflix.entity.Movie;
import com.lexdeveloper.cineflix.exception.MovieNotFoundException;
import com.lexdeveloper.cineflix.mapper.MovieMapper;
import com.lexdeveloper.cineflix.repository.MovieRepository;
import com.lexdeveloper.cineflix.resource.UsuarioResource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private UsuarioResource usuarioResource;

    private MovieMapper movieMapper = MovieMapper.INSTANCE;

    @InjectMocks
    private MovieServiceImpl movieService;

    @Test
    void whenMovieInformedWhenItShouldBeCreated(){
        //        given
        MovieDTO movieDTO = MovieDtoBuilder.builder().build().toMovieDTO();
        Movie expectedSavedMovie = movieMapper.toModel(movieDTO);

        //        when
        when(movieRepository.save(expectedSavedMovie)).thenReturn(expectedSavedMovie);
        doNothing().when(usuarioResource).setDataModificacao("Filme");

        //        then
        MessageResponseDTO msgCreated = movieService.createMovie(movieDTO);
        assertThat(msgCreated.getMessage(), is(equalTo("Created movie with ID 1")));
    }

    @Test
    void whenMovieInformedWhenItShouldBeUpdated(){
        //        given
        MovieDTO movieDTO = MovieDtoBuilder.builder().build().toMovieDTO();
        Long id = movieDTO.getId();
        Movie expectedUpdatedMovie = movieMapper.toModel(movieDTO);
        Optional<Movie> optionalMovie=Optional.of(expectedUpdatedMovie);

        //        when
        when(movieRepository.save(expectedUpdatedMovie)).thenReturn(expectedUpdatedMovie);
        when(movieRepository.findById(id)).thenReturn(optionalMovie);
        doNothing().when(usuarioResource).setDataModificacao("Filme");

        //        then
        MessageResponseDTO msgUpdated = movieService.updateMovie(id,movieDTO);
        assertThat(msgUpdated.getMessage(), is(equalTo("Updated movie with ID 1")));
    }

    @Test
    void whenMovieInformedNotFoundThenThrowsException(){
        //        given
        MovieDTO movieDTO = MovieDtoBuilder.builder().build().toMovieDTO();
        Long id = movieDTO.getId();
        Optional<Movie> optionalMovie=Optional.empty();

        //        when
        when(movieRepository.findById(id)).thenReturn(optionalMovie);

        //        then
        assertThrows(MovieNotFoundException.class, () -> movieService.updateMovie(id,movieDTO));
    }

    @Test
    void whenMovieIsSearchedWhenItShouldBeReturned(){
        // given
        MovieDTO movieDTO = MovieDtoBuilder.builder().build().toMovieDTO();
        Long id = movieDTO.getId();
        Movie expectedReturnedMovie = movieMapper.toModel(movieDTO);
        Optional<Movie> optionalMovie=Optional.of(expectedReturnedMovie);

        //  when
        when(movieRepository.findById(id)).thenReturn(optionalMovie);

        // then
        assertThat(movieDTO, is(equalTo(movieService.findById(id))));
    }



}
