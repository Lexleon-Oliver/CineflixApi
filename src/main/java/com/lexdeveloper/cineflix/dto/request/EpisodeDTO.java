package com.lexdeveloper.cineflix.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class EpisodeDTO {
    private Long id;

    @NotEmpty(message = "O campo storage não pode ficar vazio!")
    @Size(max = 255, message = "O campo storage deve ter até 255 caracteres")
    private String storage;

    @NotEmpty(message = "O campo nameEpisode não pode ficar vazio!")
    @Size(max = 255, message = "O campo nameEpisode deve ter até 255 caracteres")
    private String nameEpisode;

    private int numEpisode;

    private Long season;
}
