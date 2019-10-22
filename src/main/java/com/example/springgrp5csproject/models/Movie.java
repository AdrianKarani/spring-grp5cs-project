package com.example.springgrp5csproject.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

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

    @Enumerated(EnumType.STRING)
//    @Convert(converter = PriorityJpaConverter.class)
    @Column(name = "movie_type")
    private Type type;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JsonIgnore
    @JoinTable(name = "movie_genres",
            joinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"))
    private Set<Category> categories;

    @ManyToMany(mappedBy = "favouriteMovies", cascade = {CascadeType.ALL})
    private Set<User> usersWhoLiked;

    @ManyToMany(mappedBy = "suggestedMovies", cascade = {CascadeType.ALL})
    private Set<User> usersWhoSuggested;

    private Movie() {}

    public Movie(@NotNull(groups = Create.class) String name, @NotNull(groups = Create.class) String release_date) {
        this.name = name;
        this.release_date = release_date;
    }

//    With Type
    public Movie(@NotNull(groups = Create.class) String name, @NotNull(groups = Create.class) String release_date, Type type) {
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

//    public Category getCategory() {
//        return categories;
//    }

    public void setCategory(Category category) {
        if (this.categories == null) {
            this.categories = new HashSet<Category>() {{ add(category); }};
        } else {
            this.categories.add(category);
        }
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        if (this.categories == null) {
            this.categories = categories;
        } else {
            this.categories.addAll(categories);
        }
    }

    public Set<User> getUsersWhoLiked() {
        return usersWhoLiked;
    }

    public void setUsersWhoLiked(Set<User> usersWhoLiked) {
        this.usersWhoLiked = usersWhoLiked;
    }

    public void setUserWhoSuggested(User userWhoSuggested) {
//        this.usersWhoSuggested.add(userWhoSuggested);
        if (this.usersWhoSuggested == null) {
            this.usersWhoSuggested = new HashSet<User>() {{ add(userWhoSuggested); }};
        } else {
            this.usersWhoSuggested.add(userWhoSuggested);
        }
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
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", release_date='" + release_date + '\'' +
                ", type=" + type +
                ", categories=" + categories +
                '}';
    }
}
