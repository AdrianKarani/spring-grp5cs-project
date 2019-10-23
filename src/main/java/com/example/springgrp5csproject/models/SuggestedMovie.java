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
    private String release_date;

    @ManyToMany(mappedBy = "suggestedMovies", cascade = {CascadeType.ALL})
    private Set<User> usersWhoSuggested;

    private SuggestedMovie() {}

    public SuggestedMovie(@NotNull(groups = Create.class) String name, @NotNull(groups = Create.class) String release_date) {
        this.name = name;
        this.release_date = release_date;
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
        return "Suggested Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", release_date='" + release_date + '\'' +
                '}';
    }

    public Movie toMovie() {
        Movie movie = new Movie(name, release_date);
        movie.setId(id);
        movie.setUsersWhoSuggested(usersWhoSuggested);
        return movie;
    }
}
