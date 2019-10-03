package com.example.springgrp5csproject.repositories;

import com.example.springgrp5csproject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
