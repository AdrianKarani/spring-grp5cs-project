package com.example.springgrp5csproject.repositories;

import com.example.springgrp5csproject.models.Category;
import com.example.springgrp5csproject.models.Movie;
import com.example.springgrp5csproject.models.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Override
    List<Movie> findAll();

//    List<Movie> findMoviesByTypeEquals(Type type);
//    List<Movie> findMoviesByCategoryEquals(Category category);
//    List<Movie> findMoviesByTypeEqualsAndCategoryEquals(Type type, Category category);
}
