package com.example.springgrp5csproject.controller;

import com.example.springgrp5csproject.models.Type;
import com.example.springgrp5csproject.repositories.TypeRepository;
import com.example.springgrp5csproject.services.TypeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "types")
public class TypeController {
    private final TypeRepository typeRepository;

    public TypeController(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    @GetMapping
    public List<Type> findAll() {
        return typeRepository.findAll();
    }
}
