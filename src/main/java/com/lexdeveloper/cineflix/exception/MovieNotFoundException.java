package com.lexdeveloper.cineflix.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.function.Supplier;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MovieNotFoundException extends RuntimeException {
    public MovieNotFoundException(){
        super("O filme não foi encontrado. Não existe nenhum com esse id");
    }
}