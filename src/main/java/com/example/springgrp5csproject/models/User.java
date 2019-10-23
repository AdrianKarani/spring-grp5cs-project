package com.example.springgrp5csproject.models;

import com.example.springgrp5csproject.PriorityJpaConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull(groups = Update.class)
    @Column(name = "id")
    private Long id;

    @NotNull(groups = Create.class)
    @Column(name = "id_number", unique = true)
    private Long idNumber;

    @NotNull(groups = Create.class)
    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
//    @Convert(converter = PriorityJpaConverter.class)
    @Column(name = "user_role")
    private UserRole userRole;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JsonIgnore
    @JoinTable(name = "favourite_movies",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"))
    private Set<Movie> favouriteMovies;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JsonIgnore
    @JoinTable(name = "suggestions",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"))
    private Set<SuggestedMovie> suggestedMovies;

    private User() {}

    public User(String name, Long idNumber) {
        this.name = name;
        this.idNumber = idNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(Long idNumber) {
        this.idNumber = idNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public Set<Movie> getFavouriteMovies() {
        return favouriteMovies;
    }

    public void setFavouriteMovies(Set<Movie> favouriteMovies) {
        if (this.favouriteMovies == null) {
            this.favouriteMovies = favouriteMovies;
        } else {
            this.favouriteMovies.addAll(favouriteMovies);
        }
    }

    public void setFavouriteMovie(Movie favouriteMovie) {
        this.favouriteMovies.add(favouriteMovie);
    }

    public Set<SuggestedMovie> getSuggestedMovies() {
        return suggestedMovies;
    }

    public void setSuggestedMovies(Set<SuggestedMovie> suggestedMovies) {
        if (this.suggestedMovies == null) {
            this.suggestedMovies = suggestedMovies;
        } else {
            this.suggestedMovies.addAll(suggestedMovies);
        }
    }

    public void setSuggestedMovie(SuggestedMovie suggestedMovie) {

        this.suggestedMovies.add(suggestedMovie);
    }

    public interface Update {}

    public interface Create {}

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                "ID Number=" + idNumber +
                ", name='" + name + '\'' +
                ", userRole=" + userRole +
                ", favouriteMovies=" + favouriteMovies +
                ", suggestedMovies=" + suggestedMovies +
                '}';
    }
}
