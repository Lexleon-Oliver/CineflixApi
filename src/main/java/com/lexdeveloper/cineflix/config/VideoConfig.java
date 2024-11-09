package com.lexdeveloper.cineflix.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VideoConfig {
    @Value("${video.directory}")
    private String videoDirectory;

    public String getVideoDirectory() {
        return videoDirectory;
    }
}
