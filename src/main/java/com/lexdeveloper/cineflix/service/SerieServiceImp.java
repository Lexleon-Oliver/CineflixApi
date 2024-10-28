package com.lexdeveloper.cineflix.service;

import com.lexdeveloper.cineflix.dto.request.EpisodeDTO;
import com.lexdeveloper.cineflix.dto.request.SeasonDTO;
import com.lexdeveloper.cineflix.dto.request.SerieDTO;
import com.lexdeveloper.cineflix.dto.response.MessageResponseDTO;
import com.lexdeveloper.cineflix.entity.Episode;
import com.lexdeveloper.cineflix.entity.Season;
import com.lexdeveloper.cineflix.entity.Serie;
import com.lexdeveloper.cineflix.exception.SerieNotFoundException;
import com.lexdeveloper.cineflix.mapper.SerieMapper;
import com.lexdeveloper.cineflix.repository.EpisodeRepository;
import com.lexdeveloper.cineflix.repository.SeasonRepository;
import com.lexdeveloper.cineflix.repository.SerieRepository;
import com.lexdeveloper.cineflix.resource.UsuarioResource;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SerieServiceImp implements SerieService {
    private final SerieRepository repository;
    private final SeasonRepository seasonRepo;
    private final EpisodeRepository episodeRepo;
    private final SerieMapper mapper = SerieMapper.INSTANCE;
    private UsuarioResource userResource;

    public List<SerieDTO> listAll(Pageable pageable) {
        Page<Serie> series= repository.findAll(pageable);
        return series.stream()
                .map(mapper::toSerieDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MessageResponseDTO createSerie(SerieDTO serieDTO) {
        Serie serieToSave = mapper.toSerieModel(serieDTO);
        Serie savedSerie = repository.save(serieToSave);
        userResource.setDataModificacao("Serie");
        return createdMessageResponse(savedSerie.getId(), "Created serie with ID ");
    }

    @Override
    public MessageResponseDTO createSeason(SeasonDTO seasonDTO) {
        Season seasonToSave = mapper.toSeasonModel(seasonDTO);
        Season savedSeason = seasonRepo.save(seasonToSave);
        userResource.setDataModificacao("Serie");
        return createdMessageResponse(savedSeason.getId(), "Created season with ID ");
    }

    @Override
    public MessageResponseDTO createEpisode(EpisodeDTO episodeDTO) {
        Episode episodeToSave = mapper.toEpisodeModel(episodeDTO);
        Episode savedEpisode = episodeRepo.save(episodeToSave);
        userResource.setDataModificacao("Serie");
        return createdMessageResponse(savedEpisode.getId(),"Created episode with ID ");
    }

    @Override
    public SerieDTO findById(Long id) {
        Serie serieFound = verifyIfExists(id);
        return mapper.toSerieDTO(serieFound);
    }

    private Serie verifyIfExists(Long id) {
        return repository.findById(id)
                .orElseThrow(SerieNotFoundException::new);
    }

    private Episode verifyIfExistsEpisode(Long id) {
        return episodeRepo.findById(id)
                .orElseThrow(SerieNotFoundException::new);
    }


    public MessageResponseDTO createdMessageResponse(Long id, String message) {
        return MessageResponseDTO
                .builder()
                .message(message+id)
                .build();
    }

    public MessageResponseDTO updateSerie(Long id, SerieDTO serieDTO) {
        verifyIfExists(id);
        Serie serieToUpdate = mapper.toSerieModel(serieDTO);
        Serie updatedSerie = repository.save(serieToUpdate);
        userResource.setDataModificacao("Serie");
        return createdMessageResponse(updatedSerie.getId(), "Updated serie with ID ");
    }

}