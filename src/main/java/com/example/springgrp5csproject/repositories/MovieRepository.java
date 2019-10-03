package com.example.springgrp5csproject.repositories;

import com.example.springgrp5csproject.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
