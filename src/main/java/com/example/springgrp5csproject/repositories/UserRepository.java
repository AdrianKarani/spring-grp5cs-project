package com.example.springgrp5csproject.repositories;

import com.example.springgrp5csproject.models.Movie;
import com.example.springgrp5csproject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllBySuggestedMoviesEquals(Movie movie);
    List<User> findAllByFavouriteMoviesEquals(Movie movie);
}
