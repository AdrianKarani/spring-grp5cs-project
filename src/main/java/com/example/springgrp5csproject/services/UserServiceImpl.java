package com.example.springgrp5csproject.services;

import com.example.springgrp5csproject.exception.EntityConflictException;
import com.example.springgrp5csproject.exception.NotFoundException;
import com.example.springgrp5csproject.exception.UnauthorizedException;
import com.example.springgrp5csproject.models.*;
import com.example.springgrp5csproject.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final MovieService movieService;
    private final CategoryService categoryService;
    private final SuggestedMovieService suggestedMovieService;
    private final UserRepository userRepository;

    public UserServiceImpl(MovieService movieService, CategoryService categoryService, SuggestedMovieService suggestedMovieService, UserRepository userRepository) {
        this.movieService = movieService;
        this.categoryService = categoryService;
        this.suggestedMovieService = suggestedMovieService;
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("No User with ID: " + id + " found."));
    }

    @Override
    public User findByIdNumber(Long idNumber) {
        return userRepository.findByIdNumberEquals(idNumber);
    }

    //    User CRUD
    @Override
    public User createUser(User user) {
        user.setUserRole(UserRole.CUSTOMER);
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, User user) throws UnauthorizedException {
        User foundUser = findById(id);
        System.out.println("The Following User with ID: " + id + " is being Updated.");
        if (!foundUser.getId().equals(user.getId())) {
            throw new UnauthorizedException("Unauthorized Access to a Different User");
        }
        foundUser.setName(user.getName());
        return userRepository.save(foundUser);

    }

    @Override
    public void deleteUser(Long id, Long idNumber) throws UnauthorizedException {
        User foundUser = findById(id);
        System.out.println("The Following User with ID: " + id + " is being Deleted.");
        if (!foundUser.getIdNumber().equals(idNumber)) {
            throw new UnauthorizedException("Unauthorized Access to a Different User");
        }
        userRepository.deleteById(id);
    }

//    Complex Suggestions and Favourites
    @Override
    public List<SuggestedMovie> suggestedMovies(Long id) {
        User foundUser = findById(id);
        System.out.println("The Suggested Movies are:");
        return new ArrayList<SuggestedMovie>(foundUser.getSuggestedMovies());
    }

    @Override
    public List<Movie> favouriteMovies(Long id) {
        User foundUser = findById(id);
        System.out.println("The Liked Movies are:");
        return new ArrayList<>(foundUser.getFavouriteMovies());
    }

    @Override
    public Movie addFavourite(Long customerId, Long favouriteMovieId) throws NotFoundException {
        User foundUser = findById(customerId);
        Movie foundMovie = movieService.findById(favouriteMovieId);
        foundUser.setFavouriteMovie(foundMovie);
        userRepository.save(foundUser);
        System.out.println("The Following movie has been added to Favourites.");
        System.out.println(foundMovie.toString());
        return foundMovie;
    }

    @Override
    public Movie postMovie(Long customerId, Movie movie) throws NotFoundException, UnauthorizedException, EntityConflictException {
        User foundUser = findById(customerId);
        if (foundUser.getUserRole().equals(UserRole.ADMINISTRATOR)) {
            movie.setType(Type.ORIGINAL);
            System.out.println("The Following movie is being Suggested.");
            System.out.println(movie.toString());
            return movieService.createMovie(movie);
        } else if (foundUser.getUserRole().equals(UserRole.CUSTOMER)) {
            foundUser.setSuggestedMovie(movie.toSuggestedMovie());
            userRepository.save(foundUser);
            System.out.println("The Following movie has been Suggested.");
            System.out.println(movie.toString());
            return movie;
        }
        throw new NotFoundException("No Such User");
    }

    @Override
    public Movie updateMovie(Long customerId, Movie movie, Long movieId) throws UnauthorizedException, NotFoundException {
        validateUserRole(findById(customerId));
        System.out.println("The Following User Has been Authorized: " + findById(customerId).toString());
        return movieService.updateMovie(movieId, movie);
    }

    @Override
    public void deleteMovie(Long customerId, Long movieId) throws NotFoundException, UnauthorizedException {
        validateUserRole(findById(customerId));
        movieService.deleteMovie(movieId);
    }

    @Override
    public Movie approveSuggestion(Long customerId, Long suggestedMovieId) throws NotFoundException, UnauthorizedException, EntityConflictException {
        validateUserRole(findById(customerId));
        SuggestedMovie suggestedMovie = suggestedMovieService.findById(suggestedMovieId);
        Movie movie = suggestedMovie.toMovie();
        movie.setUsersWhoSuggested(suggestedMovie.getUsersWhoSuggested());
        System.out.println("The Following movie will be Approved to the Netflix Movie Catalogue.");
        System.out.println("Movie: " + movie.toString());
        Movie createdMovie = movieService.createMovie(movie);
        if (createdMovie != null) {
            deleteSuggestion(customerId, suggestedMovieId);
            System.out.println("The Following movie has been Approved to the Netflix Movie Catalogue.");
            System.out.println(createdMovie.toString());
        }
        return movie;
    }

//    Categories CRUD
    @Override
    public Category createCategory(Long id, Category category) throws EntityConflictException {
        validateUserRole(findById(id));
        System.out.println("The Category with ID: " + id + " is being Created.");
        return categoryService.createCategory(category);
    }

    @Override
    public void deleteCategory(Long id, Long categoryId) throws NotFoundException {
        nonExistingUser(findById(id));
        System.out.println("The Category with ID: " + categoryId + " is being Deleted.");
        categoryService.deleteCategory(categoryId);
    }

    @Override
    public Category updateCategory(Long id, Category category) throws NotFoundException {
        validateUserRole(findById(id));
        System.out.println("The Category with ID: " + id + " is being Updated.");
        return categoryService.updateCategory(category);
    }

    @Override
    public Category updateCategory(Long id, Category category, Long categoryId) throws NotFoundException {
        validateUserRole(findById(id));
        System.out.println("The Category with ID: " + categoryId + " is being Updated.");
        return categoryService.updateCategory(category, categoryId);
    }

    @Override
    public boolean deleteSuggestion(Long id, Long movieId) {
        validateUserRole(findById(id));
        if (suggestedMovieService.deleteSuggestedMovie(movieId)) {
            System.out.println("Deleted Suggested Movie");
        }
        return true;
    }

    private void validateUserRole(User user) {
        if (!user.getUserRole().equals(UserRole.ADMINISTRATOR)) {
            throw new UnauthorizedException("Unauthorized User Access");
        }
    }

    private void nonExistingUser(User user) {
        if (!user.getUserRole().equals(UserRole.ADMINISTRATOR)) {
            throw new NotFoundException("No such User");
        }
    }
}
