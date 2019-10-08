package com.example.springgrp5csproject.services;

import com.example.springgrp5csproject.exception.NotFoundException;
import com.example.springgrp5csproject.models.Type;
import com.example.springgrp5csproject.repositories.TypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {
    private final TypeRepository typeRepository;

    public TypeServiceImpl(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    @Override
    public Type findById(Long id) {
        return typeRepository.findById(id).orElseThrow(() -> new NotFoundException("No Type with ID: " + id + " found."));
    }

    @Override
    public List<Type> findAll() {
        return typeRepository.findAll();
    }

    @Override
    public Type createType(Type type) {
        return typeRepository.save(type);
    }
}
