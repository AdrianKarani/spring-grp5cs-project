package com.example.springgrp5csproject.services;

import com.example.springgrp5csproject.exception.EntityConflictException;
import com.example.springgrp5csproject.exception.NotFoundException;
import com.example.springgrp5csproject.models.Movie;
import com.example.springgrp5csproject.models.Type;
import com.example.springgrp5csproject.models.User;

import java.util.List;
import java.util.Set;

public interface MovieService {
//    CRUD
    List<Movie> findAll();
    Movie findById(Long id);
    void deleteMovie(Long id) throws NotFoundException;
    Movie createMovie(Movie movie) throws EntityConflictException;
    Movie updateMovie(Long id, Movie movie) throws NotFoundException;

//    Complex
    Movie findByName(String movieName);
    Movie findByReleaseDate(String releaseDate);
    Set<Movie> findByName(Set<String> movieNames);
    String getReleaseDate(Long movieId);
    List<Movie> likedMovies();
    List<Movie> suggestedMovies() throws Exception;
    List<Movie> approvedSuggestedMovies() throws Exception;
    List<Movie> availableMovies(String type, Long categoryId) throws NotFoundException;
    List<User> usersWhoLiked(Long movieId);
    List<User> usersWhoSuggested(Long movieId);
}
