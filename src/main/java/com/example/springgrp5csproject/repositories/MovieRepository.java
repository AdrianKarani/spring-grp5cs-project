package com.example.springgrp5csproject.repositories;

import com.example.springgrp5csproject.models.Category;
import com.example.springgrp5csproject.models.Movie;
import com.example.springgrp5csproject.models.Type;
import com.example.springgrp5csproject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    Movie findByNameEquals(String name);
    Movie findByReleaseDateEquals(String releaseDate);
    Movie findByNameEqualsAndReleaseDateEquals(String name, String releaseDate);
    List<Movie> findMoviesByTypeEquals(Type type);
    List<Movie> findAllByUsersWhoLikedIsNotNull();
    List<Movie> findAllByTypeEquals(Type type);
    List<Movie> findAllByUsersWhoLikedEquals(User user);
    List<Movie> findAllByUsersWhoSuggestedEquals(User user);
    Movie findByTypeEqualsAndCategoriesEquals(Type type, Category category);
    List<Movie> findAllByTypeEqualsAndCategoriesEquals(Type type, Category category);
}
