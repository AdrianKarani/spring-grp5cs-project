package com.example.springgrp5csproject.controller;

import com.example.springgrp5csproject.models.Movie;
import com.example.springgrp5csproject.models.User;
import com.example.springgrp5csproject.services.MovieService;
import org.springframework.web.bind.annotation.*;
import sun.rmi.runtime.Log;

import java.util.List;

@RestController
@RequestMapping(value = "movies")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

//    List all movies
    @GetMapping
    public List<Movie> findAll() {
        return movieService.findAll();
    }

//    Get One Movie
    @GetMapping("{id}")
    public Movie findMovie(@PathVariable Long id) {
        return movieService.findById(id);
    }

//    Available Movies Using Category Id and Specific Type
    @GetMapping("available/{categoryId}")
    public List<Movie> availableMovies(@PathVariable("categoryId") Long categoryId, @RequestParam String type) {
        try {
            return movieService.availableMovies(type, categoryId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

//    Get the Release Date of the Movie with the movie's name
    @GetMapping("{id}/release_date")
    public String getReleaseDate(@PathVariable("id") Long movieId) {
        return movieService.getReleaseDate(movieId);
    }

//    Get all Movies that have been Suggested
    @GetMapping("suggested")
    public List<Movie> suggestedMovies() throws Exception {
        return movieService.suggestedMovies();
    }

//    Get all Movies that have been Favoured/Liked
    @GetMapping("liked")
    public List<Movie> likedMovies() {
        return movieService.likedMovies();
    }

    //    Get all Users who Suggested this Movie Through ID
    @GetMapping("suggested/{id}/users")
    public List<User> usersWhoSuggested(@PathVariable("id") Long movieId) {
        return movieService.usersWhoSuggested(movieId);
    }

    //    Get all Users who Liked this Movie Through ID
    @GetMapping("liked/{id}/users")
    public List<User> usersWhoLiked(@PathVariable("id") Long movieId) {
        return movieService.usersWhoLiked(movieId);
    }
}
