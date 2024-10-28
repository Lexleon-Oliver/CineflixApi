package com.lexdeveloper.cineflix.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SeasonDTO {
    private Long id;

    private int numSeason;

    private Long serie;

    private String seasonBack;

    private List<EpisodeDTO> episode;
}
