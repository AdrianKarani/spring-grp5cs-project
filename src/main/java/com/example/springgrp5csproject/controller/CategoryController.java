package com.example.springgrp5csproject.controller;

import com.example.springgrp5csproject.models.Category;
import com.example.springgrp5csproject.repositories.CategoryRepository;
import com.example.springgrp5csproject.services.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

//    List All Categories
    @GetMapping
    public List<Category> getAll() {
        return categoryService.findAll();
    }

//    Individual Category
    @GetMapping("{id}")
    public Category getCategory(@PathVariable("id")Long id) {
        return categoryService.findById(id);
    }
}
