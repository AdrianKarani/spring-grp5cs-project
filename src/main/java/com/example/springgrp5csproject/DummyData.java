package com.example.springgrp5csproject;

import com.example.springgrp5csproject.models.*;
import com.example.springgrp5csproject.repositories.CategoryRepository;
import com.example.springgrp5csproject.repositories.MovieRepository;
//import com.example.springgrp5csproject.repositories.TypeRepository;
import com.example.springgrp5csproject.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class DummyData implements CommandLineRunner {
//    private final TypeRepository typeRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;
    private final CategoryRepository categoryRepository;

//    public DummyData(TypeRepository typeRepository, UserRepository userRepository, MovieRepository movieRepository, CategoryRepository categoryRepository) {
//        this.typeRepository = typeRepository;
//        this.userRepository = userRepository;
//        this.movieRepository = movieRepository;
//        this.categoryRepository = categoryRepository;
//    }

    public DummyData(UserRepository userRepository, MovieRepository movieRepository, CategoryRepository categoryRepository) {
//        this.typeRepository = typeRepository;
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
        Category fanstasy = new Category("Fantasy");
        categoryRepository.save(fanstasy);
        Category comedy = new Category("Comedy");
        categoryRepository.save(comedy);
        Category action = new Category("Action");
        categoryRepository.save(action);
        Category thriller = new Category("Thriller");
        categoryRepository.save(thriller);
        Category drama = new Category("Drama");
        categoryRepository.save(drama);
        Category cartoon = new Category("Cartoon");
        categoryRepository.save(cartoon);
        Category anime = new Category("Anime");
        categoryRepository.save(anime);
//        Types
//        Type suggested = new Type("suggested");
//        typeRepository.save(suggested);
//        Type original = new Type("original");
//        typeRepository.save(original);

//        Users
        User david = new User("David Kariuki", 91937L);
        david.setUserRole(UserRole.ADMINISTRATOR);
        userRepository.save(david);
        User ian = new User("Ian Macharia", 94566L);
        ian.setUserRole(UserRole.CUSTOMER);
        userRepository.save(ian);
        User adrian = new User("Adrian Karani", 91181L);
        adrian.setUserRole(UserRole.CUSTOMER);
        userRepository.save(adrian);
        User stan = new User("Stan Lee Lumumba", 91922L);
        stan.setUserRole(UserRole.CUSTOMER);
        userRepository.save(stan);
        User angela = new User("Angela Too", 91179L);
        angela.setUserRole(UserRole.CUSTOMER);
        userRepository.save(angela);

//        Movies
        Movie yourName = new Movie("Your Name", "26/8/2016", Type.ORIGINAL, anime);
        movieRepository.save(yourName);
        Movie joker = new Movie("Joker", "31/8/2019", Type.ORIGINAL, drama);
        movieRepository.save(joker);
        Movie kingKong = new Movie("Kong Skull Island", "28/2/2017", Type.ORIGINAL, fanstasy);
        movieRepository.save(kingKong);
        Movie godzilla = new Movie("Godzilla: King of the Monsters", "13/5/2019", Type.ORIGINAL, action);
        movieRepository.save(godzilla);

//        Assign Categories to Movie
//        yourName.setCategories(new HashSet<Category>() {{ add(anime); }});
//        movieRepository.save(yourName);
        joker.setCategories(new HashSet<Category>() {{ add(thriller); }});
        movieRepository.save(joker);
        kingKong.setCategories(new HashSet<Category>() {{ add(scifi); }});
        movieRepository.save(kingKong);
    }
}
