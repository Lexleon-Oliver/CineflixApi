package com.lexdeveloper.cineflix.builder;

import com.lexdeveloper.cineflix.dto.request.MovieDTO;
import lombok.Builder;

@Builder
public class MovieDtoBuilder {
    @Builder.Default
    private Long id = 1L;

    @Builder.Default
    private String name= "Freaks";

    @Builder.Default
    private String nameBr= "Aberrações";

    @Builder.Default
    private String year="2019";

    @Builder.Default
    private String description= "Chloe é uma menina de sete anos desesperada para escapar da proteção de seu paranoico pai" +
            " e experimentar o mundo fora dos muros de sua casa caindo aos pedaços. Quando um dia seu pai pega no sono e ela " +
            "aproveita para fugir, ela descobre um mundo lá fora cheio de mistérios e perigos muito maiores do que ela poderia imaginar.";

    @Builder.Default
    private String thumbnail= "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/bdeW3VjDtXuVXQEQMO4n9fbx8Jf.jpg";

    @Builder.Default
    private String background="https://www.themoviedb.org/t/p/original/zi5q6kXE76CQsER00Ufg9g5ki8O.jpg";

    @Builder.Default
    private String storage = "/mnt/Filmes/Filmes/'Aberracoes (2019).mp4'";

    @Builder.Default
    private String genre= " Ficção científica, Thriller, Drama, Mistério ";

    private Integer rating = 3;

    private Integer time = 90;

    private Integer minAge = 10;


    public MovieDTO toMovieDTO() {
        return new MovieDTO(id,
                name,
                nameBr,
                description,
                year,
                thumbnail,
                background,
                storage,
                genre,
                rating,
                time,
                minAge);
    }
}
