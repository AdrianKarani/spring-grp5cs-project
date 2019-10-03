package com.example.springgrp5csproject.services;

import com.example.springgrp5csproject.models.Category;

public interface CategoryService {
    Category findById(Long id);
    Category createCategory(Category category);
}
