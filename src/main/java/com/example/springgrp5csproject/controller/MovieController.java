package com.example.springgrp5csproject.controller;

import com.example.springgrp5csproject.models.Movie;
import com.example.springgrp5csproject.models.Type;
import com.example.springgrp5csproject.repositories.CategoryRepository;
import com.example.springgrp5csproject.repositories.MovieRepository;
import com.example.springgrp5csproject.services.CategoryService;
import com.example.springgrp5csproject.services.MovieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "universities")
public class MovieController {
    private final MovieService movieService;
    private final CategoryService categoryService;

    public MovieController(MovieService movieService, CategoryService categoryService) {
        this.movieService = movieService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Movie> findAll() {
        return movieService.findAll();
    }

    @PostMapping()
    public Movie createOriginalMovie(Movie movie) {
        return movieService.createMovie(movie);
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
