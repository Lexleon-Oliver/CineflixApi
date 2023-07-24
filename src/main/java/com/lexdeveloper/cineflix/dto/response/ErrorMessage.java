package com.lexdeveloper.cineflix.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErrorMessage {
    private int status;
    private String type;
    private String title;
    private String message;

}