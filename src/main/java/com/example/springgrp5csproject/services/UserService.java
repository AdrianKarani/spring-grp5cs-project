package com.example.springgrp5csproject.services;

import com.example.springgrp5csproject.exception.EntityConflictException;
import com.example.springgrp5csproject.exception.NotFoundException;
import com.example.springgrp5csproject.exception.UnauthorizedException;
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
    User updateUser(Long id, User user) throws UnauthorizedException;
    void deleteUser(Long id, Long userNumber) throws UnauthorizedException;

//    Complex Operations
    List<SuggestedMovie> suggestedMovies(Long id);
    List<Movie> favouriteMovies(Long id);
    Movie addFavourite(Long customerId, Long favouriteMovieId) throws NotFoundException;
//    Movie CRUD
    Movie postMovie(Long customerId, Movie movie) throws NotFoundException, UnauthorizedException, EntityConflictException;
    Movie updateMovie(Long customerId, Movie movie, Long movieId) throws UnauthorizedException;
    void deleteMovie(Long customerId, Long movieId) throws NotFoundException, UnauthorizedException;
    Movie approveSuggestion(Long customerId, Long suggestedMovieId, Set<Category> categories) throws NotFoundException, UnauthorizedException, EntityConflictException;

//    Category CRUD
    public Category createCategory(Long id, Category category) throws EntityConflictException;
    public void deleteCategory(Long id, Long categoryId) throws NotFoundException;
    public Category updateCategory(Long id, Category category) throws NotFoundException;
    public Category updateCategory(Long id, Category category, Long categoryId) throws NotFoundException;

    // Delete Suggested Movies
    public boolean deleteSuggestion(Long id, Long movieId);
}
