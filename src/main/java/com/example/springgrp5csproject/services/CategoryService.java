package com.example.springgrp5csproject.services;

import com.example.springgrp5csproject.models.Category;

import java.util.List;

public interface CategoryService {
    Category findById(Long id);
    Category findByName(String name);
    List<Category> findAll();
    Category createCategory(Category category) throws Exception;
    void deleteCategory(Long categoryId);
    Category updateCategory(Category category);
    Category updateCategory(Category category, Long categoryId);
}
