package com.example.springgrp5csproject.services;

import com.example.springgrp5csproject.models.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findById(Long id);
    User createUser(User user);
}
