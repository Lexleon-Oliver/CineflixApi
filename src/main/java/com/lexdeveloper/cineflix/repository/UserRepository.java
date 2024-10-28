package com.lexdeveloper.cineflix.repository;

import com.lexdeveloper.cineflix.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users,Long> {
}
