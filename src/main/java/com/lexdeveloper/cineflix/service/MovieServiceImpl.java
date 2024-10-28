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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MovieServiceImpl implements MovieService{

    private final MovieRepository repository;
    private final MovieMapper mapper = MovieMapper.INSTANCE;
    private UsuarioResource userResource;

    public List<MovieDTO> listAll(Pageable pageable) {
        Page<Movie> movies= repository.findAll(pageable);
        return movies.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public MessageResponseDTO createMovie(MovieDTO movieDTO) {
        Movie movieToSave = mapper.toModel(movieDTO);
        Movie savedMovie = repository.save(movieToSave);
        userResource.setDataModificacao("Filme");
        return createdMessageResponse(savedMovie.getId(), "Created movie with ID ");
    }
    public MovieDTO findById(Long id) {
        Movie movieFound = verifyIfExists(id);
        return mapper.toDTO(movieFound);
    }

    public MessageResponseDTO deleteMovie(Long id) {
        verifyIfExists(id);
        deleteFileFromMovie(id);
        repository.deleteById(id);
        userResource.setDataModificacao("Filme");
        return createdMessageResponse(id,"Deleted movie with ID ");
    }

    private void deleteFileFromMovie(Long id) {
        Movie movie = repository.findById(id).get();
        String url = movie.getStorage();
        try {
            String[] args = new String[] {"/bin/bash", "-c", "rm "+url, "with", "args"};
            Process proc = new ProcessBuilder(args).start();
        } catch (Exception error){
            System.out.println("Erro ao excluir arquivo de filme: "+url+" Erro: "+error);
        }
    }

    public MessageResponseDTO updateMovie(Long id, MovieDTO movieDTO) {
        verifyIfExists(id);
        Movie movieToUpdate = mapper.toModel(movieDTO);
        Movie updatedMovie = repository.save(movieToUpdate);
        userResource.setDataModificacao("Filme");
        return createdMessageResponse(updatedMovie.getId(), "Updated movie with ID ");
    }

    @Override
    public List<MovieDTO> findByYear(int intYear, Pageable pageable) {
        String year = String.valueOf(intYear);
        return repository.findMoviesByYear(year,pageable).stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<MovieDTO> findByGenre(String genre, Pageable pageable) {
        List<Movie> movies = repository.findByGenre(genre, pageable);
        return movies.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<MovieDTO> findByLast() {
        List<Movie> lastMoviesAdded= repository.findLastMovie();
        return lastMoviesAdded.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<MovieDTO> findByRecents(Pageable pageable) {
        List<MovieDTO> moviesThisYear = findByYear(setYear(), pageable);
        List<MovieDTO> moviesLastYear = findByYear(setYear() -1,pageable);
        moviesThisYear.addAll(moviesLastYear);
        return moviesThisYear;
    }

    @Override
    public List<MovieDTO> findByClassics(Pageable pageable) {
        int year = setYear() - 15;
        Page<Movie> movies= repository.findAll(pageable);
        return movies.stream()
                .filter(m -> Integer.parseInt(m.getYear()) <= year)
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    private MessageResponseDTO createdMessageResponse(Long id, String message) {
        return MessageResponseDTO
                .builder()
                .message(message+id)
                .build();
    }

    private int setYear() {
        LocalDate localDate = LocalDate.now();
        return localDate.getYear();
    }

    private Movie verifyIfExists(Long id){
        return repository.findById(id)
                .orElseThrow(MovieNotFoundException::new);
    }

}