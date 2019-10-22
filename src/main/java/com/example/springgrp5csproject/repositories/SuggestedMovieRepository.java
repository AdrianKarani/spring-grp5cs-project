package com.example.springgrp5csproject.repositories;

import com.example.springgrp5csproject.models.SuggestedMovie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuggestedMovieRepository extends JpaRepository<SuggestedMovie, Long> {
}
