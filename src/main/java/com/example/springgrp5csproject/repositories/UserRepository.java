package com.example.springgrp5csproject.repositories;

import com.example.springgrp5csproject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
//    User findByNameEquals(String name);
//    List<User> findUsersByNameContaining(String name);
}
