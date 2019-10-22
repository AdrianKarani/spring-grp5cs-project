package com.example.springgrp5csproject.services;

import com.example.springgrp5csproject.exception.NotFoundException;
import com.example.springgrp5csproject.models.Category;
import com.example.springgrp5csproject.models.User;
import com.example.springgrp5csproject.models.UserRole;
import com.example.springgrp5csproject.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final UserService userService;
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(UserService userService, CategoryRepository categoryRepository) {
        this.userService = userService;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() ->
                new NotFoundException("No Category with ID: " + id + " found."));
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category createCategory(Long id, Category category) throws Exception {
        User foundUser = userService.findById(id);
        if (foundUser.getUserRole().equals(UserRole.ADMINISTRATOR)) {
            return categoryRepository.save(category);
        } else {
            throw new Exception("Unauthorized User.");
        }
    }

    @Override
    public void deleteCategory(Long id, Long categoryId) throws Exception {
        User foundUser = userService.findById(id);
        if (foundUser.getUserRole().equals(UserRole.ADMINISTRATOR)) {
            categoryRepository.deleteById(categoryId);
            System.out.println("The Category with ID: " + categoryId + " has been deleted.");
        } else {
            throw new Exception("Unauthorized User.");
        }
    }

    @Override
    public Category updateCategory(Long id, Category category) throws Exception {
        User foundUser = userService.findById(id);
        if (foundUser.getUserRole().equals(UserRole.ADMINISTRATOR)) {
            Category foundCategory = findById(category.getId());
            foundCategory.setName(category.getName());
            return categoryRepository.save(foundCategory);
        } else {
            throw new Exception("Unauthorized User.");
        }
    }

    @Override
    public Category updateCategory(Long id, Category category, Long categoryId) throws Exception {
        User foundUser = userService.findById(id);
        if (foundUser.getUserRole().equals(UserRole.ADMINISTRATOR)) {
            Category foundCategory = findById(id);
            foundCategory.setName(category.getName());
            return categoryRepository.save(foundCategory);
        } else {
            throw new Exception("Unauthorized User.");
        }
    }
}
