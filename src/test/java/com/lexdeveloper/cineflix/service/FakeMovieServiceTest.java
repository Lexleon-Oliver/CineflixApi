package com.lexdeveloper.cineflix.service;

import com.lexdeveloper.cineflix.builder.MovieDtoBuilder;
import com.lexdeveloper.cineflix.dto.request.MovieDTO;
import com.lexdeveloper.cineflix.dto.response.MessageResponseDTO;
import com.lexdeveloper.cineflix.entity.Movie;
import com.lexdeveloper.cineflix.mapper.MovieMapper;
import com.lexdeveloper.cineflix.repository.MovieRepository;
import com.lexdeveloper.cineflix.resource.UsuarioResource;
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
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FakeMovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private UsuarioResource usuarioResource;

    private MovieMapper movieMapper = MovieMapper.INSTANCE;

    @InjectMocks
    private FakeMovieService fakeMovieService;

    @Test
    void whenMovieIdIsInformedWhenItShouldBeDeleted() throws IOException {
        //        given
        MovieDTO movieDTO = MovieDtoBuilder.builder().build().toMovieDTO();
        Long id = movieDTO.getId();
        Movie expectedDeletedMovie = movieMapper.toModel(movieDTO);
        Optional<Movie> optionalMovie=Optional.of(expectedDeletedMovie);

        //        when
        when(movieRepository.findById(id)).thenReturn(optionalMovie);
        doNothing().when(movieRepository).deleteById(id);
        doNothing().when(usuarioResource).setDataModificacao("Filme");

        //        then
        MessageResponseDTO msgDeleted = fakeMovieService.deleteMovie(id);
        assertThat(msgDeleted.getMessage(), is(equalTo("Deleted movie with ID 1")));
    }
}
