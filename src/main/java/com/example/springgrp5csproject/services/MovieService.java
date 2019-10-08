package com.example.springgrp5csproject.services;

import com.example.springgrp5csproject.models.Movie;
import com.example.springgrp5csproject.models.Type;

import java.util.List;

public interface MovieService {
    List<Movie> findAll();
    Movie findById(Long id);
    Movie createMovie(Movie movie);
    Movie updateMovie(Movie movie);
    Movie updateMovie(Long id, Movie movie);
    List<Movie> availableMovies(Type type, Long categoryId);
}
