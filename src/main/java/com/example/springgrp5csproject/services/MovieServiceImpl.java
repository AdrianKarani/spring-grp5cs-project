package com.example.springgrp5csproject.services;

import com.example.springgrp5csproject.exception.NotFoundException;
import com.example.springgrp5csproject.models.Movie;
import com.example.springgrp5csproject.models.Type;
import com.example.springgrp5csproject.models.User;
import com.example.springgrp5csproject.repositories.CategoryRepository;
import com.example.springgrp5csproject.repositories.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    private CategoryService categoryService;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
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
        return foundMovie.getRelease_date();
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
    public Movie createMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public Movie getMovie(String movieName) {
        return movieRepository.findByNameEquals(movieName);
    }

    @Override
    public Set<Movie> getMovies(Set<String> movieNames) {
        for (String movieName : movieNames) {
            Set<Movie> foundMovies = new HashSet<>();
            foundMovies.add(getMovie(movieName));
            return foundMovies;
        }
        return null;
    }

    @Override
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }

    @Override
    public Movie updateMovie(Movie movie) {
        Movie foundMovie = findById(movie.getId());
        foundMovie.setCategory(movie.getCategory());
        foundMovie.setName(movie.getName());
        foundMovie.setType(movie.getType());
        foundMovie.setRelease_date(movie.getRelease_date());
        return movieRepository.save(foundMovie);
    }

    @Override
    public Movie updateMovie(Long id, Movie movie) {
        Movie foundMovie = findById(id);
        foundMovie.setCategory(movie.getCategory());
        foundMovie.setName(movie.getName());
        foundMovie.setType(movie.getType());
        foundMovie.setRelease_date(movie.getRelease_date());
        return movieRepository.save(foundMovie);
    }

    @Override
    public List<Movie> availableMovies(Type type, Long categoryId) {
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
