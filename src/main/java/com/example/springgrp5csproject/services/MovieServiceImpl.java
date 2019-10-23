package com.example.springgrp5csproject.services;

import com.example.springgrp5csproject.exception.NotFoundException;
import com.example.springgrp5csproject.models.Movie;
import com.example.springgrp5csproject.models.Type;
import com.example.springgrp5csproject.models.User;
import com.example.springgrp5csproject.models.UserRole;
import com.example.springgrp5csproject.repositories.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    private final CategoryService categoryService;
    private final UserService userService;

    public MovieServiceImpl(MovieRepository movieRepository, CategoryService categoryService, UserService userService) {
        this.movieRepository = movieRepository;
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public Movie findById(Long id) {
        return movieRepository.findById(id).orElseThrow(() -> new NotFoundException("No Movie with ID: " + id + " found."));
    }

    @Override
    public String getReleaseDate(String movieName) {
        Movie foundMovie = movieRepository.findByNameEquals(movieName);
        return foundMovie.getReleaseDate();
    }

    @Override
    public List<Movie> likedMovies() {
        return movieRepository.findAllByUsersWhoLikedIsNotNull();
    }

    @Override
    public List<Movie> suggestedMovies() {
        return movieRepository.findAllByUsersWhoSuggestedIsNotNull();
    }

    @Override
    public Movie createMovie(Movie movie, Long idNumber) throws Exception {
        if (!userService.findByIdNumber(idNumber).getUserRole().equals(UserRole.ADMINISTRATOR)) {
            throw new Exception("Unauthorized User");
        }
        if (!movie.equals(movieRepository.findByNameEquals(movie.getName())) || !movie.equals(movieRepository.findByReleaseDateEquals(movie.getReleaseDate()))) {
            return movieRepository.save(movie);
        }
        throw new Exception("Movie already Exists.");
    }

    @Override
    public Movie findByName(String movieName) {
        return movieRepository.findByNameEquals(movieName);
    }

    @Override
    public Movie findByReleaseDate(String releaseDate) {
        return movieRepository.findByReleaseDateEquals(releaseDate);
    }

    @Override
    public Set<Movie> findByName(Set<String> movieNames) {
        for (String movieName : movieNames) {
            Set<Movie> foundMovies = new HashSet<>();
            foundMovies.add(findByName(movieName));
            return foundMovies;
        }
        return null;
    }

    @Override
    public void deleteMovie(Long id, Long idNumber) throws Exception {
        if (!userService.findByIdNumber(idNumber).getUserRole().equals(UserRole.ADMINISTRATOR)) {
            throw new Exception("Unauthorized User");
        }
        System.out.println("The Following Movie with ID: " + id + "is being Deleted.");
        movieRepository.deleteById(id);
    }

    @Override
    public Movie updateMovie(Movie movie, Long idNumber) throws Exception {
        if (!userService.findByIdNumber(idNumber).getUserRole().equals(UserRole.ADMINISTRATOR)) {
            throw new Exception("Unauthorized User");
        }
        Movie foundMovie = findById(movie.getId());
        foundMovie.setCategories(movie.getCategories());
        foundMovie.setName(movie.getName());
        foundMovie.setType(movie.getType());
        foundMovie.setReleaseDate(movie.getReleaseDate());
        return movieRepository.save(foundMovie);
    }

    @Override
    public Movie updateMovie(Long id, Movie movie, Long idNumber) throws Exception {
        if (!userService.findByIdNumber(idNumber).getUserRole().equals(UserRole.ADMINISTRATOR)) {
            throw new Exception("Unauthorized User");
        }
        Movie foundMovie = findById(id);
        foundMovie.setCategories(movie.getCategories());
        foundMovie.setName(movie.getName());
        foundMovie.setType(movie.getType());
        foundMovie.setReleaseDate(movie.getReleaseDate());
        return movieRepository.save(foundMovie);
    }

    @Override
    public List<Movie> availableMovies(String typeString, Long categoryId) {
//        System.out.println("The Type: " + typeString);
//        System.out.println("The Expected Type: " + Type.ORIGINAL.name());
        if (typeString.equals(Type.ORIGINAL.name())) {
            System.out.println("Netflix Original");
        } else if (typeString.equals(Type.SUGGESTED.name())) {
            System.out.println("User Suggested.");
        } else {
            System.out.println("Not on Catalogue");
        }
//        if (!typeString.equals(Type.ORIGINAL.name())) {
//            throw new Exception("Please Use ALL CAPS");
//        } else if (!typeString.equals(Type.SUGGESTED.name())) {
//            throw new Exception("Please Use ALL CAPS");
//        } else {
//
//        }
        Type type = Type.valueOf(typeString);
//        System.out.println("The Type: " + type.name());
//        Category category = categoryService.findById(categoryId);
//        System.out.println("The Category: " + category.toString());
        return movieRepository.findAllByTypeEqualsAndCategoriesEquals(type, categoryService.findById(categoryId));
    }

    @Override
    public List<User> usersWhoLiked(Long movieId) {
        Movie foundMovie = findById(movieId);
        return new ArrayList<>(foundMovie.getUsersWhoLiked());
    }

    @Override
    public List<User> usersWhoSuggested(Long movieId) {
        Movie foundMovie = findById(movieId);
        return new ArrayList<>(foundMovie.getUsersWhoSuggested());
    }
}
