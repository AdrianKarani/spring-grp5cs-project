package com.example.springgrp5csproject.services;

import com.example.springgrp5csproject.exception.NotFoundException;
import com.example.springgrp5csproject.models.Category;
import com.example.springgrp5csproject.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() ->
                new NotFoundException("No Category with ID: " + id + " found."));
    }

    @Override
    public Category findByName(String name) {
        return categoryRepository.findByNameEquals(name);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category createCategory(Category category) throws Exception {
//        User foundUser = userService.findById(id);
        if (!category.equals(categoryRepository.findByNameEquals(category.getName()))) {
            return categoryRepository.save(category);
        }
        throw new Exception("Category already Exists.");
//        if (foundUser.getUserRole().equals(UserRole.ADMINISTRATOR)) {
//            return categoryRepository.save(category);
//        } else {
//            throw new Exception("Unauthorized User.");
//        }
    }

    @Override
    public void deleteCategory(Long categoryId) {
//        User foundUser = userService.findById(id);
        categoryRepository.deleteById(categoryId);
//        if (foundUser.getUserRole().equals(UserRole.ADMINISTRATOR)) {
//            categoryRepository.deleteById(categoryId);
//            System.out.println("The Category with ID: " + categoryId + " has been deleted.");
//        } else {
//            throw new Exception("Unauthorized User.");
//        }
    }

    @Override
    public Category updateCategory(Category category) {
//        User foundUser = userService.findById(id);
        Category foundCategory = findById(category.getId());
        foundCategory.setName(category.getName());
        return categoryRepository.save(foundCategory);
//        if (foundUser.getUserRole().equals(UserRole.ADMINISTRATOR)) {
//            Category foundCategory = findById(category.getId());
//            foundCategory.setName(category.getName());
//            return categoryRepository.save(foundCategory);
//        } else {
//            throw new Exception("Unauthorized User.");
//        }
    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {
//        User foundUser = userService.findById(id);
        Category foundCategory = findById(categoryId);
        foundCategory.setName(category.getName());
        return categoryRepository.save(foundCategory);
//        if (foundUser.getUserRole().equals(UserRole.ADMINISTRATOR)) {
//            Category foundCategory = findById(categoryId);
//            foundCategory.setName(category.getName());
//            return categoryRepository.save(foundCategory);
//        } else {
//            throw new Exception("Unauthorized User.");
//        }
    }
}
