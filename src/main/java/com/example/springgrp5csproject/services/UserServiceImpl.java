package com.example.springgrp5csproject.services;

import com.example.springgrp5csproject.exception.NotFoundException;
import com.example.springgrp5csproject.models.Movie;
import com.example.springgrp5csproject.models.Type;
import com.example.springgrp5csproject.models.User;
import com.example.springgrp5csproject.models.UserRole;
import com.example.springgrp5csproject.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final MovieService movieService;
    private final UserRepository userRepository;

    public UserServiceImpl(MovieService movieService, UserRepository userRepository) {
        this.movieService = movieService;
        this.userRepository = userRepository;
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
        userRepository.deleteById(id);
    }

    @Override
    public List<Movie> suggestedMovies(Long id) {
        User foundUser = findById(id);
        return new ArrayList<>(foundUser.getSuggestedMovies());
    }

    @Override
    public List<Movie> favouriteMovies(Long id) {
        User foundUser = findById(id);
        return new ArrayList<>(foundUser.getFavouriteMovies());
    }

    @Override
    public void addFavourite(Long customerId, String favouriteMovieName) {
        User foundUser = findById(customerId);
        Movie foundMovie = movieService.getMovie(favouriteMovieName);
        if (movieService.getMovie(favouriteMovieName) != null) {
            foundUser.setFavouriteMovie(foundMovie);
        }
//        System.out.println(foundMovie.toString());
//        System.out.println(foundUser.toString());
        userRepository.save(foundUser);
    }

    @Override
    public void addFavourites(Long customerId, Set<String> favouriteMovieNames) {
        User foundUser = findById(customerId);
        Set<Movie> foundMovies = movieService.getMovies(favouriteMovieNames);
        foundUser.setFavouriteMovies(foundMovies);
        userRepository.save(foundUser);
    }

    @Override
    public void suggestMovie(Long customerId, Movie suggestedMovie) {
        User foundUser = findById(customerId);
        suggestedMovie.setType(Type.SUGGESTED);
        foundUser.setSuggestedMovie(suggestedMovie);
        userRepository.save(foundUser);
    }

    @Override
    public void suggestMovies(Long customerId, Set<Movie> suggestedMovies) {
        User foundUser = findById(customerId);
        for (Movie movie : suggestedMovies) {
            movie.setType(Type.SUGGESTED);
        }
        foundUser.setSuggestedMovies(suggestedMovies);
        userRepository.save(foundUser);
    }

    @Override
    public void approveSuggestion(Long customerId, Movie suggestedMovie) {
        User foundUser = findById(customerId);
        if (foundUser.getUserRole().equals(UserRole.ADMINISTRATOR)) {
            movieService.createMovie(suggestedMovie);
        }
    }

    @Override
    public void approveSuggestions(Long customerId, Set<Movie> suggestedMovies) {
        User foundUser = findById(customerId);
        if (foundUser.getUserRole().equals(UserRole.ADMINISTRATOR)) {
            for (Movie movie : suggestedMovies) {
                movieService.createMovie(movie);
            }
        }
    }
}
