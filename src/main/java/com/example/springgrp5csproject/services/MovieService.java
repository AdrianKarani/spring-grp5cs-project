package com.example.springgrp5csproject.services;

import com.example.springgrp5csproject.models.Movie;
import com.example.springgrp5csproject.models.Type;
import com.example.springgrp5csproject.models.User;

import java.util.List;
import java.util.Set;

public interface MovieService {
//    CRUD
    List<Movie> findAll();
    Movie findById(Long id);
    void deleteMovie(Long id) throws Exception;
    Movie createMovie(Movie movie) throws Exception;
//    Movie updateMovie(Movie movie, Long idNumber) throws Exception;
    Movie updateMovie(Long id, Movie movie) throws Exception;

//    Complex
    Movie findByName(String movieName);
    Movie findByReleaseDate(String releaseDate);
    Set<Movie> findByName(Set<String> movieNames);
    String getReleaseDate(String movieName);
    List<Movie> likedMovies();
    List<Movie> suggestedMovies();
    List<Movie> availableMovies(String type, Long categoryId) throws Exception;
    List<User> usersWhoLiked(Long movieId);
    List<User> usersWhoSuggested(Long movieId);
}
