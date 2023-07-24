package com.lexdeveloper.cineflix.mapper;

import com.lexdeveloper.cineflix.dto.request.EpisodeDTO;
import com.lexdeveloper.cineflix.dto.request.SeasonDTO;
import com.lexdeveloper.cineflix.dto.request.SerieDTO;
import com.lexdeveloper.cineflix.entity.Episode;
import com.lexdeveloper.cineflix.entity.Season;
import com.lexdeveloper.cineflix.entity.Serie;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.function.Function;

@Mapper
public interface SerieMapper {

    SerieMapper INSTANCE = Mappers.getMapper(SerieMapper.class);

    Serie toSerieModel(SerieDTO serieDTO);
    SerieDTO toSerieDTO(Serie serie);

    Season toSeasonModel(SeasonDTO seasonDTO);
    SeasonDTO toSeasonDTO (Season season);
    List<Season> toSeasonListModel(List<SeasonDTO> seasonDTOList);
    List<SeasonDTO> toSeasonListDTO(List<Season> seasons);


    Episode toEpisodeModel(EpisodeDTO episodeDTO);
    EpisodeDTO toEpisodeDTO (Episode episode);
    List<Episode> toEpisodeListModel(List<EpisodeDTO> episodeDTOList);
    List<EpisodeDTO> toEpisodeListDTO(List<Episode> episodes);

}
