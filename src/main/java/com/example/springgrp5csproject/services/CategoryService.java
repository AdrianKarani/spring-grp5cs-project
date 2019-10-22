package com.example.springgrp5csproject.services;

import com.example.springgrp5csproject.models.Category;

import java.util.List;

public interface CategoryService {
    Category findById(Long id);
    List<Category> findAll();
    Category createCategory(Long id, Category category) throws Exception;
    void deleteCategory(Long id, Long categoryId) throws Exception;
    Category updateCategory(Long id, Category category) throws Exception;
    Category updateCategory(Long id, Category category, Long categoryId) throws Exception;
}
