package com.lexdeveloper.cineflix.service;

import com.lexdeveloper.cineflix.dto.request.EpisodeDTO;
import com.lexdeveloper.cineflix.dto.request.SeasonDTO;
import com.lexdeveloper.cineflix.dto.request.SerieDTO;
import com.lexdeveloper.cineflix.dto.response.MessageResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SerieService {
    List<SerieDTO> listAll(Pageable pageable);

    MessageResponseDTO createSerie(SerieDTO serieDTO);

    MessageResponseDTO createSeason(SeasonDTO seasonDTO);

    MessageResponseDTO createEpisode(EpisodeDTO episodeDTO);

    SerieDTO findById(Long id);

    MessageResponseDTO updateSerie(Long id, SerieDTO serieDTO);
}
