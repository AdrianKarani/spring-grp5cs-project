package com.example.springgrp5csproject.services;

import com.example.springgrp5csproject.exception.EntityConflictException;
import com.example.springgrp5csproject.exception.NotFoundException;
import com.example.springgrp5csproject.models.Movie;
import com.example.springgrp5csproject.models.SuggestedMovie;
import com.example.springgrp5csproject.models.Type;
import com.example.springgrp5csproject.models.User;
import com.example.springgrp5csproject.repositories.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    private final SuggestedMovieService suggestedMovieService;
    private final CategoryService categoryService;

    public MovieServiceImpl(MovieRepository movieRepository, SuggestedMovieService suggestedMovieService, CategoryService categoryService) {
        this.movieRepository = movieRepository;
        this.suggestedMovieService = suggestedMovieService;
        this.categoryService = categoryService;
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
    public String getReleaseDate(Long movieId) {
        Movie foundMovie = findById(movieId);
        return foundMovie.getReleaseDate();
    }

    @Override
    public List<Movie> likedMovies() {
        return movieRepository.findAllByUsersWhoLikedIsNotNull();
    }

    @Override
    public List<Movie> suggestedMovies() throws Exception {
        System.out.println("Finding the List of Pending Suggested Movies.");
        int i = 0;
        List<Movie> movies = new ArrayList<Movie>();
        List<SuggestedMovie> suggestedMovies = suggestedMovieService.findAllSuggestedMovies();
        System.out.println("List of Suggested Movie: ");
        while (i < suggestedMovies.size()) {
            Movie movie = suggestedMovies.get(i).toMovie();
            System.out.println(movie);
            movies.add(movie);
            i++;
        }
        if (movies == null) {
            throw new Exception("No Suggested Movies");
        }
        return movies;
    }

    @Override
    public List<Movie> approvedSuggestedMovies() throws Exception {
        System.out.println("Finding the List of Approved Suggested Movies.");
        int i = 0;
        List<Movie> movies = movieRepository.findAllByTypeEquals(Type.SUGGESTED);
        List<Movie> approvedMovies = new ArrayList<Movie>();
        System.out.println("List of Approved Suggested Movie: ");
        while (i != movies.size()) {
            Movie movie = movies.get(i);
            System.out.println(movie);
            approvedMovies.add(movie);
            i++;
        }
        return movies;
    }

    @Override
    public Movie createMovie(Movie movie) throws EntityConflictException {
        if (!movie.equals(movieRepository.findByNameEquals(movie.getName())) || !movie.equals(movieRepository.findByReleaseDateEquals(movie.getReleaseDate()))) {
            return movieRepository.save(movie);
        }
        throw new EntityConflictException("Movie already Exists.");
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
    public void deleteMovie(Long id) {
        if (findById(id) == null) {
            throw new NotFoundException("No Such Movie with ID: " + id);
        }
        System.out.println("The Following Movie with ID: " + id + " is being Deleted.");
        movieRepository.deleteById(id);
    }

    @Override
    public Movie updateMovie(Long id, Movie movie) throws NotFoundException {
        System.out.println("The Following Movie with ID: " + id + " is being Updated.");
        Movie foundMovie = findById(id);
        System.out.println("The Movie Being Updated is: " + foundMovie.toString());
        if (movie.getCategories() != null) {
            foundMovie.setCategories(movie.getCategories());
        } else if (movie.getType() != null) {
            foundMovie.setType(movie.getType());
        }
        foundMovie.setName(movie.getName());
        foundMovie.setReleaseDate(movie.getReleaseDate());
        return movieRepository.save(foundMovie);
    }

    @Override
    public List<Movie> availableMovies(String typeString, Long categoryId) throws NotFoundException {
        if (typeString.equals(Type.ORIGINAL.name())) {
            System.out.println("Netflix Original");
        } else if (typeString.equals(Type.SUGGESTED.name())) {
            System.out.println("User Suggested.");
        } else {
            throw new NotFoundException("Not on Catalogue.");
        }
        Type type = Type.valueOf(typeString);
        return movieRepository.findAllByTypeEqualsAndCategoriesEquals(type, categoryService.findById(categoryId));
    }

    @Override
    public List<User> usersWhoLiked(Long movieId) {
        Movie foundMovie = findById(movieId);
        return new ArrayList<>(foundMovie.getUsersWhoLiked());
    }

    @Override
    public List<User> usersWhoSuggested(Long movieId) {
        SuggestedMovie foundSuggestedMovie = suggestedMovieService.findById(movieId);
//        Movie foundMovie = findById(movieId);
        return new ArrayList<>(foundSuggestedMovie.getUsersWhoSuggested());
    }
}
