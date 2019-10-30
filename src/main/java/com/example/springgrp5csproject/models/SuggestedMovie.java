package com.example.springgrp5csproject.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    private String releaseDate;

    @ManyToMany(mappedBy = "suggestedMovies", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<User> usersWhoSuggested;

    private SuggestedMovie() {}

    public SuggestedMovie(@NotNull(groups = Create.class) String name, @NotNull(groups = Create.class) String releaseDate) {
        this.name = name;
        this.releaseDate = releaseDate;
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

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setUserWhoSuggested(User usersWhoSuggested) {
        this.usersWhoSuggested.add(usersWhoSuggested);
    }

    public Set<User> getUsersWhoSuggested() {
        return usersWhoSuggested;
    }

    public void setUsersWhoSuggested(Set<User> usersWhoSuggested) {
        if (this.usersWhoSuggested == null) {
            this.usersWhoSuggested = usersWhoSuggested;
        } else {
            this.usersWhoSuggested.addAll(usersWhoSuggested);
        }
    }

    public interface Update {}

    public interface Create {}

    @Override
    public String toString() {
        return "Suggested Movie { " +
                "id = " + id +
                ", name = '" + name + '\'' +
                ", release_date = '" + releaseDate + '\'' +
                '}';
    }

    public Movie toMovie() {
        Movie movie = new Movie(name, releaseDate);
        movie.setId(this.getId());
        movie.setType(Type.SUGGESTED);
        movie.setUsersWhoSuggested(usersWhoSuggested);
        return movie;
    }
}
