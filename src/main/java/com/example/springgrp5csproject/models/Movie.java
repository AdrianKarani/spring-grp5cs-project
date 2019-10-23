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
    private String releaseDate;

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

    public Movie(@NotNull(groups = Create.class) String name, @NotNull(groups = Create.class) String releaseDate) {
        this.name = name;
        this.releaseDate = releaseDate;
    }

//    With Type
    public Movie(@NotNull(groups = Create.class) String name, @NotNull(groups = Create.class) String releaseDate, Type type) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.type = type;
    }

//    With Category String
    public Movie(@NotNull(groups = Create.class) String name, @NotNull(groups = Create.class) String releaseDate, String categoryName) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.categories = new HashSet<Category>() {{ add(new Category(categoryName)); }};
    }

//    With Both Type and Category String
    public Movie(@NotNull(groups = Create.class) String name, @NotNull(groups = Create.class) String releaseDate, Type type, String categoryName) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.type = type;
        this.categories = new HashSet<Category>() {{ add(new Category(categoryName)); }};
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
            System.out.println("Adding a Category to empty set");
            this.categories = new HashSet<Category>() {{ add(category); }};
        } else {
            System.out.println("Adding a Category to existing set");
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
                ", release_date='" + releaseDate + '\'' +
                ", type=" + type +
                ", categories=" + categories +
                '}';
    }
}
