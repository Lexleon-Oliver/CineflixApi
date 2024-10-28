package com.lexdeveloper.cineflix.service;

import com.lexdeveloper.cineflix.dto.request.MovieDTO;
import com.lexdeveloper.cineflix.dto.response.MessageResponseDTO;
import com.lexdeveloper.cineflix.entity.Movie;
import com.lexdeveloper.cineflix.exception.MovieNotFoundException;
import com.lexdeveloper.cineflix.mapper.MovieMapper;
import com.lexdeveloper.cineflix.repository.MovieRepository;
import com.lexdeveloper.cineflix.resource.UsuarioResource;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FakeMovieService{

    private final MovieRepository repository;
    private final MovieMapper mapper = MovieMapper.INSTANCE;
    private UsuarioResource userResource;

    public MessageResponseDTO deleteMovie(Long id) {
        verifyIfExists(id);
        deleteFileFromMovie(id);
        repository.deleteById(id);
        userResource.setDataModificacao("Filme");
        return createdMessageResponse(id,"Deleted movie with ID ");
    }

    void deleteFileFromMovie(Long id) {

    }

    private Movie verifyIfExists(Long id){
        return repository.findById(id)
                .orElseThrow(MovieNotFoundException::new);
    }

    private MessageResponseDTO createdMessageResponse(Long id, String message) {
        return MessageResponseDTO
                .builder()
                .message(message+id)
                .build();
    }
}
