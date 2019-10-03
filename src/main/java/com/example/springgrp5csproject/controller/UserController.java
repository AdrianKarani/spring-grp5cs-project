package com.example.springgrp5csproject.controller;

import com.example.springgrp5csproject.models.User;
import com.example.springgrp5csproject.services.UserService;
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

    @PostMapping
    User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }
}
