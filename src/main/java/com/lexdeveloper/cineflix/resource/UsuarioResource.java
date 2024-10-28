package com.lexdeveloper.cineflix.resource;

import com.lexdeveloper.cineflix.dto.request.MovieDTO;
import com.lexdeveloper.cineflix.dto.response.LoginResponse;
import com.lexdeveloper.cineflix.dto.response.MessageResponseDTO;
import com.lexdeveloper.cineflix.entity.Users;
import com.lexdeveloper.cineflix.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping
@CrossOrigin
public class UsuarioResource {

    private final UserRepository userRepository;


    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponse login() {
        List<Users> users;
        users = userRepository.findAll();
        return LoginResponse.builder()
                .users(users)
                .build();
    }

    @PutMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponseDTO updateUser(@PathVariable Long id, @Valid @RequestBody Users user) {
        userRepository.save(user);
        return createdMessageResponse(user.getId(), "Updated user with ID ");
    }

    public void setDataModificacao(String midia){
        LocalDateTime localDateTime = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String formattedDateTime = localDateTime.format(formatter);
        List<Users> users =userRepository.findAll();
        for (Users user : users) {
            if (midia.equals("Filme")){
                user.setMoviesLastModified(formattedDateTime);
            }
            if (midia.equals("Serie")){
                user.setSeriesLastModified(formattedDateTime);
            }
            userRepository.save(user);
        }
    }

    private MessageResponseDTO createdMessageResponse(Long id, String message) {
        return MessageResponseDTO
                .builder()
                .message(message+id)
                .build();
    }



}
