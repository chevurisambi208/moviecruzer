package com.ashok.moviecruiserapp.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ashok.moviecruiserapp.domain.Movie;
import com.ashok.moviecruiserapp.exception.MovieAlreadyExistsException;
import com.ashok.moviecruiserapp.exception.MovieNotFoundException;
import com.ashok.moviecruiserapp.repository.MovieRepository;
import com.ashok.moviecruiserapp.service.MovieServiceImpl;


 public class MovieServiceTest {

	@Mock
	private MovieRepository movieRepo;

	@InjectMocks
	private MovieServiceImpl movieServiceImpl;

	private Movie movie;

	Optional<Movie> options;

	@Before
	public void setupMock() {
		MockitoAnnotations.initMocks(this);
		movie = new Movie("1234",1, "POC", "good Movie", "www.abc.com", "2015-03-31", 45.5, 112,"Jhon123");
		options = Optional.of(movie);

	}

	@Test
	public void testMockCreation() {
		assertNotNull(movie);
		assertNotNull("jpaRepository creation fails: use @injectMocks on movieServicempl", movieRepo);
	}

	@Test
	public void testSaveMovieSuccess() throws Exception {
		when(movieRepo.save(movie)).thenReturn(movie);
		boolean flag = movieServiceImpl.saveMovie(movie);
		assertEquals("saving movie failed , the call to movieDAOImpl is returning false ,check this method", true,
				flag);
		verify(movieRepo, times(1)).save(movie);
		verify(movieRepo, times(1)).findById(movie.getId());
	}

	@Test(expected = MovieAlreadyExistsException.class)
	public void testSaveMovieFailure() throws MovieAlreadyExistsException {
		when(movieRepo.findById(1)).thenReturn(options);
		when(movieRepo.save(movie)).thenReturn(movie);
		final boolean flag = movieServiceImpl.saveMovie(movie);

	}

	@Test
	public void testUpdateMovie() throws MovieNotFoundException {

		when(movieRepo.findById(1)).thenReturn(options);
		when(movieRepo.save(movie)).thenReturn(movie);
		movie.setComments("not so good movie");
		Movie movie1 = movieServiceImpl.updateMovie(1,movie);
		assertEquals("updating movie failed", "not so good movie", movie1.getComments());
		verify(movieRepo, times(1)).save(movie);
		verify(movieRepo, times(1)).findById(movie.getId());
	}

	@Test
	public void testDeleteMovieById() throws Exception {
		when(movieRepo.findById(1)).thenReturn(options);
		doNothing().when(movieRepo).delete(movie);
		boolean flag = movieServiceImpl.deleteMovieById(1);
		assertEquals("deleting movie failed", true, flag);
		verify(movieRepo, times(1)).delete(movie);
		verify(movieRepo, times(1)).findById(movie.getId());
	}

	@Test
	public void testGetMovieById() throws Exception {
		when(movieRepo.findById(1)).thenReturn(options);
		Movie movie1 = movieServiceImpl.getMovieById(1);
		assertEquals("fetching movie by id failed", movie1, movie);
		verify(movieRepo, times(1)).findById(movie.getId());
	}

	@Test
	public void testGetMyMovies() throws Exception {
		List<Movie> movieList = new ArrayList<>(1);
		movieList.add(movie);
		when(movieRepo.findByUserId("ashu")).thenReturn(movieList);
		List<Movie> movies1 = movieServiceImpl.getMyMovies("ashu");
		assertEquals(movieList, movies1);
		verify(movieRepo, times(1)).findByUserId("ashu");

	}

	
}