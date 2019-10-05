package com.example.springgrp5csproject.repositories;

import com.example.springgrp5csproject.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAll();
    Optional<Category> findById(Long categoryId);
    Category findByIdEquals(Long categoryId);
}
