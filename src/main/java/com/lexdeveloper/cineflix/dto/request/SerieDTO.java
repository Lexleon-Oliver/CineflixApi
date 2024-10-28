package com.lexdeveloper.cineflix.dto.request;

import com.lexdeveloper.cineflix.entity.Season;
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
public class SerieDTO {
    private Long id;

    @NotEmpty(message = "O campo name não pode ficar vazio!")
    @Size(max = 255, message = "O campo name deve ter até 255 caracteres")
    private String name;

    @NotEmpty(message = "O campo nameBr não pode ficar vazio!")
    @Size(max = 255, message = "O campo nameBr deve ter até 255 caracteres")
    private String nameBr;

    @NotEmpty(message = "O campo year não pode ficar vazio!")
    @Size(min = 4,max = 4, message = "O campo year deve ter apenas 4 caracteres")
    private String year;

    @NotEmpty(message = "O campo description não pode ficar vazio!")
    @Size(max = 700, message = "O campo description deve ter até 700 caracteres")
    private String description;

    @NotEmpty(message = "O campo thumbnail não pode ficar vazio!")
    @Size(max = 255, message = "O campo thumbnail deve ter até 255 caracteres")
    private String thumbnail;

    @NotEmpty(message = "O campo background não pode ficar vazio!")
    @Size(max = 255, message = "O campo background deve ter até 255 caracteres")
    private String background;

    @NotEmpty(message = "O campo genre não pode ficar vazio!")
    @Size(max = 255, message = "O campo genre deve ter até 255 caracteres")
    private String genre;

    private Integer rating;

    private Integer minAge;

    private Integer time;

    private boolean completed;

    private List<SeasonDTO> season;
}
