package com.example.springgrp5csproject.services;

import com.example.springgrp5csproject.exception.NotFoundException;
import com.example.springgrp5csproject.models.*;
import com.example.springgrp5csproject.repositories.CategoryRepository;
import com.example.springgrp5csproject.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final MovieService movieService;
    private final CategoryService categoryService;
    private final SuggestedMovieService suggestedMovieService;
    private final UserRepository userRepository;
//    private final CategoryRepository categoryRepository;

    public UserServiceImpl(MovieService movieService, CategoryService categoryService, SuggestedMovieService suggestedMovieService, UserRepository userRepository) {
        this.movieService = movieService;
        this.categoryService = categoryService;
        this.suggestedMovieService = suggestedMovieService;
        this.userRepository = userRepository;
//        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("No User with ID: " + id + " found."));
    }

    @Override
    public User createUser(User user) {
        user.setUserRole(UserRole.CUSTOMER);
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        User foundUser = findById(user.getId());
        foundUser.setName(user.getName());
        return userRepository.save(foundUser);
    }

    @Override
    public User updateUser(Long id, User user) {
        User foundUser = findById(id);
        foundUser.setName(user.getName());
        return userRepository.save(foundUser);
    }

    @Override
    public void deleteUser(Long id) {
        System.out.println("The Following User with ID: " + id + "is being Deleted.");
        userRepository.deleteById(id);
    }

    @Override
    public List<SuggestedMovie> suggestedMovies(Long id) {
        User foundUser = findById(id);
        System.out.println("The Suggested Movies are:");
        return new ArrayList<SuggestedMovie>(foundUser.getSuggestedMovies());
    }

    @Override
    public List<Movie> favouriteMovies(Long id) {
        User foundUser = findById(id);
        System.out.println("The Liked Movies are:");
        return new ArrayList<>(foundUser.getFavouriteMovies());
    }

    @Override
    public Movie addFavourite(Long customerId, String favouriteMovieName) {
        User foundUser = findById(customerId);
        Movie foundMovie = movieService.getMovie(favouriteMovieName);
        if (movieService.getMovie(favouriteMovieName) != null) {
            foundUser.setFavouriteMovie(foundMovie);
        }
        userRepository.save(foundUser);
        System.out.println("The Following movie has been added to Favourites.");
        System.out.println(foundMovie.toString());
        return foundMovie;
    }

    @Override
    public void addFavourites(Long customerId, Set<String> favouriteMovieNames) {
        User foundUser = findById(customerId);
        Set<Movie> foundMovies = movieService.getMovies(favouriteMovieNames);
        foundUser.setFavouriteMovies(foundMovies);
        userRepository.save(foundUser);
        System.out.println("The Following movies have been added to Favourites.");
        System.out.println(foundMovies.toString());
    }

    @Override
    public SuggestedMovie suggestMovie(Long customerId, SuggestedMovie suggestedMovie) {
        User foundUser = findById(customerId);
        foundUser.setSuggestedMovie(suggestedMovie);
        userRepository.save(foundUser);
        System.out.println("The Following movie has been Suggested.");
        System.out.println(suggestedMovie.toString());
        return suggestedMovie;
    }

    @Override
    public void suggestMovies(Long customerId, Set<SuggestedMovie> suggestedMovies) {
        User foundUser = findById(customerId);
        foundUser.setSuggestedMovies(suggestedMovies);
        userRepository.save(foundUser);
        System.out.println("The Following movies have been Suggested.");
        System.out.println(suggestedMovies.toString());
    }

    @Override
    public Movie approveSuggestion(Long customerId, Long suggestedMovieId) {
        User foundUser = findById(customerId);
        if (foundUser.getUserRole().equals(UserRole.ADMINISTRATOR)) {
            SuggestedMovie suggestedMovie = suggestedMovieService.findById(suggestedMovieId);
            Movie movie = new Movie(suggestedMovie.getName(), suggestedMovie.getRelease_date(), Type.SUGGESTED);
            movie.setUserWhoSuggested(foundUser);
            movieService.createMovie(movie);
            System.out.println("The Following movie have been Approved to the Netflix Movie Catalogue.");
            System.out.println(movie.toString());
            return movie;
        }
        return null;
    }

//    Categories CRUD
    @Override
    public Category createCategory(Long id, Category category) throws Exception {
        User foundUser = findById(id);
        if (foundUser.getUserRole().equals(UserRole.ADMINISTRATOR)) {
            return categoryService.createCategory(category);
        } else {
            throw new Exception("Unauthorized User.");
        }
    }

    @Override
    public void deleteCategory(Long id, Long categoryId) throws Exception {
        User foundUser = findById(id);
        if (foundUser.getUserRole().equals(UserRole.ADMINISTRATOR)) {
            categoryService.deleteCategory(categoryId);
            System.out.println("The Category with ID: " + categoryId + " has been deleted.");
        } else {
            throw new Exception("Unauthorized User.");
        }
    }

    @Override
    public Category updateCategory(Long id, Category category) throws Exception {
        User foundUser = findById(id);
        if (foundUser.getUserRole().equals(UserRole.ADMINISTRATOR)) {
            return categoryService.updateCategory(category);
        } else {
            throw new Exception("Unauthorized User.");
        }
    }

    @Override
    public Category updateCategory(Long id, Category category, Long categoryId) throws Exception {
        User foundUser = findById(id);
        if (foundUser.getUserRole().equals(UserRole.ADMINISTRATOR)) {
            return categoryService.updateCategory(category, categoryId);
        } else {
            throw new Exception("Unauthorized User.");
        }
    }
}
