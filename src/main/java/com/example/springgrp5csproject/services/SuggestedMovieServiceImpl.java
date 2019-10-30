package com.example.springgrp5csproject.services;

import com.example.springgrp5csproject.exception.NotFoundException;
import com.example.springgrp5csproject.models.SuggestedMovie;
import com.example.springgrp5csproject.repositories.SuggestedMovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuggestedMovieServiceImpl implements SuggestedMovieService {
    private final SuggestedMovieRepository suggestedMovieRepository;

    public SuggestedMovieServiceImpl(SuggestedMovieRepository suggestedMovieRepository) {
        this.suggestedMovieRepository = suggestedMovieRepository;
    }

    @Override
    public SuggestedMovie findById(Long id) {
        return suggestedMovieRepository.findById(id).orElseThrow(() -> new NotFoundException("No Suggested Movie with ID: " + id + " found."));
    }

    @Override
    public List<SuggestedMovie> findAllSuggestedMovies() {
        return suggestedMovieRepository.findAll();
    }

    @Override
    public boolean deleteSuggestedMovie(Long id) {
        if (findById(id) == null) {
            throw new NotFoundException("Suggested Movie is Not Found with ID: " + id);
        }
        System.out.println("The Following movie will be Deleted From Pending. With ID: " + id);
        System.out.println("The Following movie will be Deleted From Pending. With ID: " + findById(id).toString());
        suggestedMovieRepository.deleteById(id);
        return true;
    }
}
