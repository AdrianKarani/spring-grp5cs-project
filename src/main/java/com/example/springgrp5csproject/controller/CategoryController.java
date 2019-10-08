package com.example.springgrp5csproject.controller;

import com.example.springgrp5csproject.models.Category;
import com.example.springgrp5csproject.repositories.CategoryRepository;
import com.example.springgrp5csproject.services.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> getAll() {
        return categoryService.findAll();
    }

    @PostMapping()
    public Category createCategory(Category category) {
        return categoryService.createCategory(category);
    }
}
