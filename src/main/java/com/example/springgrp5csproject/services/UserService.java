package com.example.springgrp5csproject.services;

import com.example.springgrp5csproject.models.Movie;
import com.example.springgrp5csproject.models.SuggestedMovie;
import com.example.springgrp5csproject.models.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    List<User> findAll();
    User findById(Long id);
    User createUser(User user);
    User updateUser(User user);
    User updateUser(Long id, User user);
    void deleteUser(Long id);
    List<SuggestedMovie> suggestedMovies(Long id);
    List<Movie> favouriteMovies(Long id);
    void addFavourite(Long customerId, String favouriteMovieName);
    void addFavourites(Long customerId, Set<String> favouriteMovieNames);
    void suggestMovie(Long customerId, SuggestedMovie suggestedMovie);
    void suggestMovies(Long customerId, Set<SuggestedMovie> suggestedMovie);
    void approveSuggestion(Long customerId, Long suggestedMovieId);
}
