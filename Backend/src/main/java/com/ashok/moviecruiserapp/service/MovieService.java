package com.ashok.moviecruiserapp.service;

import java.util.List;

import com.ashok.moviecruiserapp.domain.Movie;
import com.ashok.moviecruiserapp.exception.MovieAlreadyExistsException;
import com.ashok.moviecruiserapp.exception.MovieNotFoundException;

public interface MovieService {

	boolean saveMovie(Movie movie) throws MovieAlreadyExistsException;

	Movie updateMovie(int id,Movie movie) throws MovieNotFoundException;

	boolean deleteMovieById(int id) throws MovieNotFoundException;

	Movie getMovieById(int id) throws MovieNotFoundException;

	List<Movie> getMyMovies(String userId);

}
