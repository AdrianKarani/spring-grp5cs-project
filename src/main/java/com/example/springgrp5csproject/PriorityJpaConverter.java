package com.example.springgrp5csproject;

import javafx.scene.layout.Priority;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class PriorityJpaConverter implements AttributeConverter<Priority, String> {

    @Override
    public String convertToDatabaseColumn(Priority attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.toString();
    }

    @Override
    public Priority convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        try {
            return Priority.valueOf(dbData);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
