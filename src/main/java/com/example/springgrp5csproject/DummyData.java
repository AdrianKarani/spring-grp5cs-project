package com.example.springgrp5csproject;

import com.example.springgrp5csproject.models.Category;
import com.example.springgrp5csproject.models.Movie;
import com.example.springgrp5csproject.models.Type;
import com.example.springgrp5csproject.models.User;
import com.example.springgrp5csproject.repositories.CategoryRepository;
import com.example.springgrp5csproject.repositories.MovieRepository;
import com.example.springgrp5csproject.repositories.TypeRepository;
import com.example.springgrp5csproject.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DummyData implements CommandLineRunner {
    private final TypeRepository typeRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;
    private final CategoryRepository categoryRepository;

    public DummyData(TypeRepository typeRepository, UserRepository userRepository, MovieRepository movieRepository, CategoryRepository categoryRepository) {
        this.typeRepository = typeRepository;
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
//        Categories
        Category horror = new Category("Horror");
        categoryRepository.save(horror);
        Category scifi = new Category("Sci-Fi");
        categoryRepository.save(scifi);
        Category comedy = new Category("Comedy");
        categoryRepository.save(comedy);
        Category action = new Category("Action");
        categoryRepository.save(action);
        Category thriller = new Category("Thriller");
        categoryRepository.save(thriller);
        Category cartoon = new Category("Cartoon");
        categoryRepository.save(cartoon);
        Category anime = new Category("Anime");
        categoryRepository.save(anime);
//        Types
        Type suggested = new Type("suggested");
        typeRepository.save(suggested);
        Type original = new Type("original");
        typeRepository.save(original);

//        Users
        User david = new User("David Kariuki");
        userRepository.save(david);

//        Movies
        Movie yourName = new Movie("Your Name", "26/8/2016");
        movieRepository.save(yourName);
    }
}
