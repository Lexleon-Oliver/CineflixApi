package com.lexdeveloper.cineflix.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SerieNotFoundException extends RuntimeException {
    public SerieNotFoundException(){
        super("A série não foi encontrado. Não existe nenhuma com esse id");
    }
}