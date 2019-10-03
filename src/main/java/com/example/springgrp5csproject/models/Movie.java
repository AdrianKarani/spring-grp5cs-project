package com.example.springgrp5csproject.models;

import javax.persistence.*;

@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "release_date")
    private String release_date;

    @ManyToOne
    private Type type;

    @OneToMany
    private Category category;
}
