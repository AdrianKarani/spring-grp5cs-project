package com.example.springgrp5csproject.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "suggested_movies")
public class SuggestedMovie {
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

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "movie_type")
    private Type type;

    @ManyToMany(mappedBy = "suggestedMovies", cascade = {CascadeType.ALL})
    private Set<User> usersWhoSuggested;

    private SuggestedMovie() {}

    public SuggestedMovie(@NotNull(groups = Create.class) String name, @NotNull(groups = Create.class) String release_date) {
        this.name = name;
        this.release_date = release_date;
    }

    //    With Type
    public SuggestedMovie(@NotNull(groups = Create.class) String name, @NotNull(groups = Create.class) String release_date, Type type) {
        this.name = name;
        this.release_date = release_date;
        this.type = type;
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

    public Set<User> getUsersWhoSuggested() {
        return usersWhoSuggested;
    }

    public void setUsersWhoSuggested(Set<User> usersWhoSuggested) {
        this.usersWhoSuggested = usersWhoSuggested;
    }

    public interface Update {}

    public interface Create {}

    @Override
    public String toString() {
        return "Suggested Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", release_date='" + release_date + '\'' +
                ", type=" + type +
                '}';
    }
}
