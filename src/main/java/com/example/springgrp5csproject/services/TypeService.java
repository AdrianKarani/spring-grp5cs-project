package com.example.springgrp5csproject.services;

import com.example.springgrp5csproject.models.Type;

public interface TypeService {
    Type findById(Long id);
    Type createType(Type type);
}
