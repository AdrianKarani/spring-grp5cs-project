package com.example.springgrp5csproject.controller;

import com.example.springgrp5csproject.exception.EntityConflictException;
import com.example.springgrp5csproject.exception.NotFoundException;
import com.example.springgrp5csproject.exception.UnauthorizedException;
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
    @PutMapping(value = "{id}")
    public User updateUser(@PathVariable("id") Long id, @Validated(User.Update.class) @RequestBody User user) throws UnauthorizedException {
        return userService.updateUser(id, user);
    }

//    Delete Account
    @DeleteMapping(value = "{id}/idNumber/{idNumber}")
    public void deleteUser(@PathVariable(name = "id") Long id, @PathVariable(name = "idNumber") Long idNumber) throws Exception {
        userService.deleteUser(id, idNumber);
    }


//    Movies
//    User can either suggest a Movie to Netflix if a normal Customer, or create a Movie, if they are an Admin
    @PostMapping("{id}/movies")
    public Movie postMovie(@PathVariable(name = "id") Long id, @RequestBody Movie movie) throws Exception {
        return userService.postMovie(id, movie);
    }

    //    Update Movie by Netflix Admin with an ID
    @PutMapping(value = "{id}/movies/{movieId}")
    public Movie updateMovie(@PathVariable(name = "id") Long id, @Validated(Movie.Update.class)@RequestBody Movie movie, @PathVariable Long movieId) throws NotFoundException, UnauthorizedException {
        return userService.updateMovie(id, movie, movieId);
    }

    //    Delete Movie
    @DeleteMapping(value = "{id}/movies/{movieId}")
    public void deleteMovie(@PathVariable(name = "id") Long id, @PathVariable(name = "movieId") Long movieId) throws NotFoundException, UnauthorizedException { userService.deleteMovie(id, movieId); }


//    Complex Operations
//    User adds a movie to Liked/Favourite list of movies
    @PostMapping("{id}/movies/like")
    public Movie addFavourite(@PathVariable("id") Long id, @RequestParam(name = "movieId") Long movieId) throws Exception {
        return userService.addFavourite(id, movieId);
    }

//    List all Movies that User has Suggested
    @GetMapping("{id}/movies/suggested")
    public List<SuggestedMovie> getSuggestedMovies(@PathVariable(name = "id") Long id) {
        return userService.suggestedMovies(id);
    }

//    List User's Favourite Movies
    @GetMapping("{id}/movies/liked")
    public List<Movie> getFavouriteMovies(@PathVariable(name = "id") Long id) {
        return userService.favouriteMovies(id);
    }

//    Categories
//    Create a Category
    @PostMapping("{id}/category")
    public Category createCategory(@PathVariable(name = "id") Long id, @RequestBody Category category) throws EntityConflictException {
        return userService.createCategory(id, category);
    }

//    Delete a Category
    @DeleteMapping("{id}/category/{categoryId}")
    public void deleteCategory(@PathVariable(name = "id") Long id, @PathVariable(name = "categoryId") Long categoryId) throws NotFoundException {
        userService.deleteCategory(id, categoryId);
    }

//    Update Category with ID
    @PutMapping("{id}/category/{categoryId}")
    public Category updateCategory(@PathVariable(name = "id") Long id, @RequestBody Category category, @PathVariable(name = "categoryId") Long categoryId) throws NotFoundException {
        return userService.updateCategory(id, category, categoryId);
    }

//    Approve Suggested Movie
    @PostMapping("{id}/movies/suggested/{movieId}")
    public Movie approveSuggestion(@PathVariable(name = "id") Long id, @PathVariable(name = "movieId") Long movieId) throws NotFoundException, UnauthorizedException, EntityConflictException {
        return userService.approveSuggestion(id, movieId);
    }

//    Delete Suggested Movie
    @DeleteMapping("{id}/movies/suggested/pending/{movieId}")
    public void deleteSuggestion(@PathVariable("id") Long id, @PathVariable("movieId") Long movieId) {
        userService.deleteSuggestion(id, movieId);
    }
}
