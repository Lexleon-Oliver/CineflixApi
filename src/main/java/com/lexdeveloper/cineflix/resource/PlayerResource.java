package com.lexdeveloper.cineflix.resource;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.lexdeveloper.cineflix.entity.Episode;
import com.lexdeveloper.cineflix.entity.Movie;
import com.lexdeveloper.cineflix.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.lang.reflect.Type;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/player/play")
@CrossOrigin
public class PlayerResource {

    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    public void setUrl(@RequestBody String url) {
        executePlayer(url);
    }

    private void executePlayer(String url){
        try {
//            Comando para abrir o filme com o player de video padrão
            String[] args = new String[] {"/bin/bash", "-c", "xdg-open "+url, "with", "args"};
            Process proc = new ProcessBuilder(args).start();
        } catch (Exception error){
            System.out.println("Erro ao abrir filme: "+url+" Erro: "+error);
        }
    }

}


