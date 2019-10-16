package com.example.springgrp5csproject.controller;

import com.example.springgrp5csproject.models.Movie;
import com.example.springgrp5csproject.models.User;
import com.example.springgrp5csproject.repositories.UserRepository;
import com.example.springgrp5csproject.services.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

//    Find All Users
    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }

//    Register a User
    @PostMapping()
    public User createUser(@Validated(User.Create.class)@RequestBody User user) {
        return userService.createUser(user);
    }

//    Update User
    @PatchMapping()
    public User updateUser(@Validated(User.Update.class)@RequestBody User user) {
        return userService.updateUser(user);
    }

//    Update User with ID
    @PatchMapping(value = "{id}")
    public User updateUser(@PathVariable("id") Long id, @Validated(User.Update.class) @RequestBody User user) {
        return userService.updateUser(user);
    }

    //    Delete Account
    @DeleteMapping(value = "{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

//    User can suggest a Movie to Netflix
    @PostMapping("{id}/movie_suggestion")
    public void suggestMovie(@PathVariable("id") Long customerId, @RequestBody Movie movie) {
        userService.suggestMovie(customerId, movie);
    }

//    TODO: User can suggest Movies to Netflix
    @PostMapping("{id}/movie_suggestions")
    public void suggestMovies(@PathVariable("id") Long customerId, @RequestBody Set<Movie> suggestedMovies) {
        userService.suggestMovies(customerId, suggestedMovies);
    }

//    User adds a movie to Favourites list
    @PostMapping("{id}/movie_favourite")
    public void addFavourite(@PathVariable("id") Long customerId, @RequestBody String movieName) {
        userService.addFavourite(customerId, movieName);
    }

//    TODO: User adds movies to Favourites list
    @PostMapping("{id}/movie_favourites")
    public void addFavourite(@PathVariable("id") Long customerId, @RequestBody Set<String> movieNames) {
        userService.addFavourites(customerId, movieNames);
    }

//    TODO: List all Movies that User has Suggested
    @GetMapping("{id}/suggested_movies")
    public List<Movie> getSuggestedMovies(@PathVariable("id") Long customerId) {
        return userService.suggestedMovies(customerId);
    }

//    TODO: List User's Favourite Movies
    @GetMapping("{id}/favourite_movies")
    public List<Movie> getFavouriteMovies(@PathVariable("id") Long customerId) {
        return userService.favouriteMovies(customerId);
    }
}
