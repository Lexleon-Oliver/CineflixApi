package com.lexdeveloper.cineflix.dto.response;

import com.lexdeveloper.cineflix.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private List<Users> users;
}
