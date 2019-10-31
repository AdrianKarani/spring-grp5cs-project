package com.example.springgrp5csproject.services;

import com.example.springgrp5csproject.models.SuggestedMovie;

import java.util.List;

public interface SuggestedMovieService {
    SuggestedMovie findById(Long id);
    SuggestedMovie findByName(String name);
    List<SuggestedMovie> findAllSuggestedMovies();
    boolean deleteSuggestedMovie(Long id);
}
