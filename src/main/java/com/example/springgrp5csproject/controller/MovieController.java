package com.example.springgrp5csproject.controller;

import com.example.springgrp5csproject.models.Movie;
import com.example.springgrp5csproject.models.Type;
import com.example.springgrp5csproject.repositories.CategoryRepository;
import com.example.springgrp5csproject.repositories.MovieRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "universities")
public class MovieController {
    private final MovieRepository movieRepository;
    private final CategoryRepository categoryRepository;

    public MovieController(MovieRepository movieRepository, CategoryRepository categoryRepository) {
        this.movieRepository = movieRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @PostMapping()
    public Movie createOriginalMovie(Movie movie) {
        return movieRepository.save(movie);
    }

//    @PostMapping()
//    public Movie suggestMovie(Movie suggestedMovie) {
//        return movieRepository.save(suggestedMovie);
//    }

//    @GetMapping("{categoryId}")
//    public List<Movie> availableMovies(@PathVariable("categoryId") Long categoryId, @RequestParam Type type) {
//        return movieRepository.findMoviesByTypeEqualsAndCategoryEquals(type, categoryRepository.findByIdEquals(categoryId));
//    }
}
