package com.lexdeveloper.cineflix.resource;

import com.lexdeveloper.cineflix.dto.response.ErrorMessage;
import com.lexdeveloper.cineflix.exception.MovieNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ErrorResource extends ResponseEntityExceptionHandler {
    @ExceptionHandler(MovieNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage resourceNotFound(Exception ex, WebRequest req){
        return new ErrorMessage(404, ex.getLocalizedMessage(), "Não Encontrado", "Erro de Recurso Não Encontrado");
    }

    @ExceptionHandler(Exception.class)
    public ErrorMessage exceptionResource(Exception ex, WebRequest req){
        return new ErrorMessage(500, ex.getLocalizedMessage(), "Erro Interno","Erro Interno do Servidor");
    }
}
