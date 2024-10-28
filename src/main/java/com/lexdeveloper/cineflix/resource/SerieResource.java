package com.lexdeveloper.cineflix.resource;

import com.lexdeveloper.cineflix.dto.request.EpisodeDTO;
import com.lexdeveloper.cineflix.dto.request.SeasonDTO;
import com.lexdeveloper.cineflix.dto.request.SerieDTO;
import com.lexdeveloper.cineflix.dto.response.MessageResponseDTO;
import com.lexdeveloper.cineflix.service.SerieService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/series")
public class SerieResource {
    private SerieService service;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<SerieDTO> allSeries(Pageable pageable) {
        return service.listAll(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createSerie(@RequestBody @Valid SerieDTO serieDTO){
        return service.createSerie(serieDTO);
    }

    @PostMapping("/seasons")
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createSeason(@RequestBody @Valid SeasonDTO seasonDTO){
        return service.createSeason(seasonDTO);
    }

    @PostMapping("/seasons/episodes")
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createEpisode(@RequestBody @Valid EpisodeDTO episodeDTO){
        return service.createEpisode(episodeDTO);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SerieDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponseDTO updateSerie(@PathVariable Long id, @Valid @RequestBody SerieDTO serieDTO){
        return service.updateSerie(id,serieDTO);
    }

}
