package com.example.springgrp5csproject.controller;

import com.example.springgrp5csproject.models.Movie;
import com.example.springgrp5csproject.models.User;
import com.example.springgrp5csproject.repositories.UserRepository;
import com.example.springgrp5csproject.services.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }

    @PostMapping()
    public User createUser(@Validated(User.Create.class)@RequestBody User user) {
        return userService.createUser(user);
    }

    @PostMapping("movie_suggest")
    public Movie suggestMovie(@RequestBody Movie movie) {
        return userService.suggestMovie(movie);
    }

    @DeleteMapping(value = "{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
