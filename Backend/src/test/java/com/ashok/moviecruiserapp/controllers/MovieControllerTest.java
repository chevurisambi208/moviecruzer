package com.ashok.moviecruiserapp.controllers;


import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;

import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ashok.moviecruiserapp.controller.MovieController;
import com.ashok.moviecruiserapp.domain.Movie;
import com.ashok.moviecruiserapp.service.MovieService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(MovieController.class)

public class MovieControllerTest {

	private MockMvc movieMockMvc;

	@MockBean
	private MovieService movieService;

	@InjectMocks
	private MovieController movieController;

	private Movie movie;

	static List<Movie> movies;

	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);
		movie = new Movie("1234",3706,"Batman Begins", "Very good action movie", "22/11/2015",null,4.9,33,"Jhon123");
		movieMockMvc = MockMvcBuilders.standaloneSetup(movieController).build();
		movies = new ArrayList<>();
		movie = new Movie("2341",1, "POC", "good Movie", "www.abc.com", "2015-03-23", 45.5, 112, "Jhon123");
		movies.add(movie);
		movie = new Movie("5641",2, "POC-2", "very good Movie", "www.cde.com", "2013-09-23", 65.5, 110, "Jhon123");
		movies.add(movie);
	}

	@Ignore
	@Test
	public void testSaveNewMovie() throws Exception {
		when(movieService.saveMovie(movie)).thenReturn(true);
		String token ="eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMDEiLCJpYXQiOjE1MjUyNDIxMzh9.buWJGgnjO1-Y6nZywegEUsZm1Injzw3B0dNASLKEx6Q";
		movieMockMvc.perform(post("/api/v1/movieservice/movie").header("authorization", "Bearer "+token)
		.contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie))).andExpect(status().isCreated())
		.andDo(print());
		verify(movieService, times(1)).saveMovie(Mockito.any(Movie.class));
	}
	@Ignore
	@Test
	public void testDeleteMovieById() throws Exception {

		when(movieService.deleteMovieById(1)).thenReturn(true);
		movieMockMvc.perform(delete("/api/v1/movieservice/movie/{id}", 1)).andExpect(status().isOk());
		verify(movieService, times(1)).deleteMovieById(1);
		verifyNoMoreInteractions(movieService);

	}
   
	@Ignore
	@Test
	public void testGetMyMovies() throws Exception {
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMDEiLCJpYXQiOjE1MjUyNDIxMzh9.buWJGgnjO1-Y6nZywegEUsZm1Injzw3B0dNASLKEx6Q";
		when(movieService.getMyMovies("100")).thenReturn(movies);
		movieMockMvc.perform(get("/api/v1/movieservice/movies").header("authorization", "Bearer "+token)
	 .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
		.andDo(print());
		verify(movieService, times(1)).getMyMovies("100");
		verifyNoMoreInteractions(movieService);
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
