package com.example.springgrp5csproject.repositories;

import com.example.springgrp5csproject.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
