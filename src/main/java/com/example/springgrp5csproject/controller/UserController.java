package com.example.springgrp5csproject.controller;

import com.example.springgrp5csproject.models.Category;
import com.example.springgrp5csproject.models.Movie;
import com.example.springgrp5csproject.models.SuggestedMovie;
import com.example.springgrp5csproject.models.User;
import com.example.springgrp5csproject.services.CategoryService;
import com.example.springgrp5csproject.services.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "users")
public class UserController {
    private final UserService userService;
    private final CategoryService categoryService;
    public UserController(UserService userService, CategoryService categoryService) {
        this.userService = userService;
        this.categoryService = categoryService;
    }

//    User CRUD
//    Find All Users
    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }

//    View One User
    @GetMapping("{id}")
    public User findUser(@PathVariable("id")Long id) {
        return userService.findById(id);
    }

//    Register a User
    @PostMapping()
    public User createUser(@Validated(User.Create.class)@RequestBody User user) {
        return userService.createUser(user);
    }

//    Update User with ID
    @PatchMapping(value = "{id}")
    public User updateUser(@PathVariable("id") Long id, @Validated(User.Update.class) @RequestBody User user) throws Exception {
        return userService.updateUser(id, user);
    }

//    Delete Account
    @DeleteMapping(value = "{id}/idNumber/{idNumber}")
    public void deleteUser(@PathVariable Long id, @PathVariable Long idNumber) throws Exception {
        userService.deleteUser(id, idNumber);
    }


//    Movies
//    User can either suggest a Movie to Netflix if a normal Customer, or create a Movie, if they are an Admin
    @PostMapping("{id}/movies")
    public Movie postMovie(@PathVariable("id") Long customerId, @RequestBody SuggestedMovie suggestedMovie) throws Exception {
        return userService.postMovie(customerId, suggestedMovie);
    }

    //    Update Movie by Netflix Admin with an ID
    @PatchMapping(value = "{userId}/movies/{movieId}")
    public Movie updateMovie(@PathVariable Long userId, @Validated(Movie.Update.class)@RequestBody Movie movie, @PathVariable Long movieId) throws Exception {
        return userService.updateMovie(userId, movie, movieId);
    }

    //    Delete Movie
    @DeleteMapping(value = "{userId}/movies/{movieId}")
    public void deleteMovie(@PathVariable Long userId, @PathVariable Long movieId) throws Exception { userService.deleteMovie(userId, movieId); }


//    Complex Operations
//    User adds a movie to Liked/Favourite list of movies
    @PostMapping("{id}/movies/like")
    public Movie addFavourite(@PathVariable("id") Long customerId, @RequestParam Long movieId) throws Exception {
        return userService.addFavourite(customerId, movieId);
    }

//    List all Movies that User has Suggested
    @GetMapping("{id}/movies/suggested")
    public List<SuggestedMovie> getSuggestedMovies(@PathVariable("id") Long customerId) {
        return userService.suggestedMovies(customerId);
    }

//    List User's Favourite Movies
    @GetMapping("{id}/movies/liked")
    public List<Movie> getFavouriteMovies(@PathVariable("id") Long customerId) {
        return userService.favouriteMovies(customerId);
    }

//    Create a Category
    @PostMapping("{id}/category")
    public Category createCategory(@PathVariable("id") Long id, @RequestBody Category category) throws Exception {
        return userService.createCategory(id, category);
    }

//    Delete a Category
    @DeleteMapping("{id}/category/{categoryId}")
    public void deleteCategory(@PathVariable("id") Long id, @PathVariable("categoryId") Long categoryId) throws Exception {
        userService.deleteCategory(id, categoryId);
    }

//    Update Category with ID
    @PatchMapping("{id}/category/{categoryId}")
    public Category updateCategory(@PathVariable("id") Long id, @RequestBody Category category, @PathVariable("categoryId") Long categoryId) throws Exception {
        return userService.updateCategory(id, category, categoryId);
    }

//    Approve Suggested Movie
    @PostMapping("{id}/movies/suggested/{movie_id}")
    public Movie approveSuggestion(@PathVariable("id") Long customerId, @PathVariable("movie_id") Long movieId) throws Exception {
        return userService.approveSuggestion(customerId, movieId);
    }
}
