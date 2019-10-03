package com.example.springgrp5csproject.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull(groups = Update.class)
    @Column(name = "id")
    private Long id;

    @NotNull(groups = Create.class)
    @Column(name = "name")
    private String name;

    @NotNull(groups = Create.class)
    @Column(name = "release_date")
    private String release_date;

    @ManyToOne
    private Type type;

    @OneToMany
    private Category category;

    private Movie() {}

    public Movie(String name, String release_date, Type type, Category category) {
        this.name = name;
        this.release_date = release_date;
        this.type = type;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public interface Update {}

    public interface Create {}

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", release_date='" + release_date + '\'' +
                ", type=" + type +
                ", category=" + category +
                '}';
    }
}
