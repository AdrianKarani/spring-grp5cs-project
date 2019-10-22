package com.example.springgrp5csproject.controller;

import com.example.springgrp5csproject.models.Movie;
import com.example.springgrp5csproject.models.Type;
import com.example.springgrp5csproject.models.User;
import com.example.springgrp5csproject.services.CategoryService;
import com.example.springgrp5csproject.services.MovieService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "movies")
public class MovieController {
    private final MovieService movieService;
    private final CategoryService categoryService;

    public MovieController(MovieService movieService, CategoryService categoryService) {
        this.movieService = movieService;
        this.categoryService = categoryService;
    }

//    Return all movies
    @GetMapping
    public List<Movie> findAll() {
        return movieService.findAll();
    }

//    Create Original Movie by Netflix Administrator
    @PostMapping()
    public Movie createOriginalMovie(@Validated(Movie.Create.class)@RequestBody Movie movie) {
        return movieService.createMovie(movie);
    }

//    Update Movie by Netflix Admin
    @PatchMapping()
    public Movie updateMovie(@Validated(Movie.Update.class)@RequestBody Movie movie) {
        return movieService.updateMovie(movie);
    }

//    Update Movie by Netflix Admin with an ID
    @PatchMapping(value = "{id}")
    public Movie updateMovie(@PathVariable Long id, @Validated(Movie.Update.class)@RequestBody Movie movie) {
        return movieService.updateMovie(id, movie);
    }

//    Delete Movie
    @DeleteMapping(value = "{id}")
    public void deleteMovie(@PathVariable Long id) { movieService.deleteMovie(id); }

//    Use Category Id and Specific Type
    @GetMapping("available_movies/{categoryId}")
    public List<Movie> availableMovies(@PathVariable("categoryId") Long categoryId, @RequestParam String type) {
        try {
            return movieService.availableMovies(type, categoryId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

//    Get the Release Date of the Movie with the movie's name
    @GetMapping("movieRelease/{movieName}")
    public String getReleaseDate(@PathVariable("movieName") String movieName) {
        return movieService.getReleaseDate(movieName);
    }

//    Get the Movie with the movie's name
    @GetMapping("{movieName}")
    public Movie getMovie(@PathVariable("movieName") String movieName) {
        return movieService.getMovie(movieName);
    }

//    Get all Movies that have been Suggested
    @GetMapping("suggested_movies")
    public List<Movie> suggestedMovies() {
        return movieService.suggestedMovies();
    }

//    Get all Movies that have been Favoured/Liked
    @GetMapping("liked_movies")
    public List<Movie> likedMovies() {
        return movieService.likedMovies();
    }

    //    Get all Users who Suggested this Movie Through ID
    @GetMapping("{movieId}/users_who_suggested")
    public List<User> usersWhoSuggested(@PathVariable("movieId") Long movieId) {
        return movieService.usersWhoSuggested(movieId);
    }

    //    Get all Users who Suggested this Movie Through Name
//    @GetMapping("{movieName}/users")
//    public List<Movie> usersWhoSuggested(@PathVariable("movieName") String movieName) {
//        return movieService.suggestedMovies();
//    }

    //    Get all Users who Liked this Movie Through ID
    @GetMapping("{movieId}/users_who_liked")
    public List<User> usersWhoLiked(@PathVariable("movieId") Long movieId) {
        return movieService.usersWhoLiked(movieId);
    }

    //    Get all Users who Liked this Movie Through Name
//    @GetMapping("{movieName}/users")
//    public List<Movie> usersWhoLiked() {
//        return movieService.likedMovies();
//    }
}
