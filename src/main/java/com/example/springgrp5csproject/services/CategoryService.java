package com.example.springgrp5csproject.services;

import com.example.springgrp5csproject.exception.EntityConflictException;
import com.example.springgrp5csproject.exception.NotFoundException;
import com.example.springgrp5csproject.models.Category;

import java.util.List;

public interface CategoryService {
    Category findById(Long id);
    Category findByName(String name);
    List<Category> findAll();
    Category createCategory(Category category) throws EntityConflictException;
    void deleteCategory(Long categoryId) throws NotFoundException;
    Category updateCategory(Category category) throws NotFoundException;
    Category updateCategory(Category category, Long categoryId) throws NotFoundException;
}
