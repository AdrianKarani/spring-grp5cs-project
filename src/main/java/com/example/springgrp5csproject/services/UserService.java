package com.example.springgrp5csproject.services;

import com.example.springgrp5csproject.models.Category;
import com.example.springgrp5csproject.models.Movie;
import com.example.springgrp5csproject.models.SuggestedMovie;
import com.example.springgrp5csproject.models.User;

import java.util.List;
import java.util.Set;

public interface UserService {
//    Simple CRUD
    List<User> findAll();
    User findById(Long id);
    User findByIdNumber(Long idNumber);
    User createUser(User user);
    User updateUser(Long id, User user) throws Exception;
    void deleteUser(Long id, Long userNumber) throws Exception;

//    Complex Operations
    List<SuggestedMovie> suggestedMovies(Long id);
    List<Movie> favouriteMovies(Long id);
    Movie addFavourite(Long customerId, Long favouriteMovieId) throws Exception;
//    Movie CRUD
    Movie postMovie(Long customerId, SuggestedMovie suggestedMovie) throws Exception;
    Movie updateMovie(Long customerId, Movie movie, Long movieId) throws Exception;
    void deleteMovie(Long customerId, Long movieId) throws Exception;
    Movie approveSuggestion(Long customerId, Long suggestedMovieId) throws Exception;

//    Category CRUD
    public Category createCategory(Long id, Category category) throws Exception;
    public void deleteCategory(Long id, Long categoryId) throws Exception;
    public Category updateCategory(Long id, Category category) throws Exception;
    public Category updateCategory(Long id, Category category, Long categoryId) throws Exception;
}
