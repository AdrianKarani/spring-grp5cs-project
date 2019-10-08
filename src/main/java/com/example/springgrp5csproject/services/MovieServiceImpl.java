package com.example.springgrp5csproject.services;

import com.example.springgrp5csproject.exception.NotFoundException;
import com.example.springgrp5csproject.models.Movie;
import com.example.springgrp5csproject.repositories.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;

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
    public Movie createMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public Movie updateMovie(Movie movie) {
        Movie foundMovie = findById(movie.getId());
//        foundMovie.setCategory(movie.getCategory());
//        foundMovie.setName(movie.getName());
        foundMovie.setType(movie.getType());
//        foundMovie.setRelease_date(movie.getRelease_date());
        return movieRepository.save(foundMovie);
    }

    @Override
    public Movie updateMovie(Long id, Movie movie) {
        Movie foundMovie = findById(id);
        foundMovie.setType(movie.getType());
        return movieRepository.save(foundMovie);
    }
}
