package com.example.springgrp5csproject.services;

import com.example.springgrp5csproject.models.Type;

import java.util.List;

public interface TypeService {
    Type findById(Long id);
    List<Type> findAll();
    Type createType(Type type);
}
