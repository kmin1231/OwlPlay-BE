package io.owl_browthers.owlplay.controller;


import io.owl_browthers.owlplay.service.MovieService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/fetch-movies")
    public String fetchMovies() {
        movieService.fetchAndSaveMovies(2023);
        movieService.fetchAndSaveMovies(2024);
        movieService.fetchAndSaveMovieDetails();
        return "Movies fetched and saved successfully";
    }
}