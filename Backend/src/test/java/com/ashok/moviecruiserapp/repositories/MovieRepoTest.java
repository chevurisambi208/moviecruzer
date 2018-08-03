package com.ashok.moviecruiserapp.repositories;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ashok.moviecruiserapp.domain.Movie;
import com.ashok.moviecruiserapp.repository.MovieRepository;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Transactional
public class MovieRepoTest {
	
	@Autowired
	private transient MovieRepository repo;
	public void setRepo(final MovieRepository repo) {
		this.repo = repo;
	}
	
	@Ignore
	@Test
	public void testSaveMovie() throws Exception {
		repo.save(new Movie("1234",1, "POC", "good Movie", "www.abc.com", "2015-03-23", 45.5, 112,"Jhon123"));
		final Movie movie = repo.getOne(1);
		assertThat(movie.getId()).isEqualTo(1);
	}
	
	@Ignore
	@Test
	public void testUpdateMovie() throws Exception {
		repo.save(new Movie("4321",1, "POC", "good Movie", "www.abc.com", "2015-03-23", 45.5, 112,"Jhon123"));
		final Movie movie = repo.getOne(1);
		assertEquals(movie.getName(), "POC");
		movie.setComments("hi");
		repo.save(movie);
		final Movie tempMovie = repo.getOne(1);
		assertEquals("hi", tempMovie.getComments());
		repo.delete(tempMovie);
	}
	
	@Ignore
	@Test
	public void testDeleteMovie() throws Exception {
		repo.save(new Movie("1234",1, "POC", "good Movie", "www.abc.com", "2015-03-23", 45.5, 112,"Jhon123"));
		final Movie movie = repo.getOne(1);
		assertEquals(movie.getName(), "POC");
		repo.delete(movie);
		assertEquals(Optional.empty(), repo.findById(1));
	}
	
	@Ignore
	@Test
	public void testGetMovie() throws Exception {
		repo.save(new Movie("1234",1, "POC", "good Movie", "www.abc.com", "2015-03-23", 45.5, 112,"Jhon123"));
		final Movie movie = repo.getOne(1);
		assertEquals(movie.getName(), "POC");
	}
	
	@Ignore
   @Test
	public void testGetMyMovies() {
		
		repo.save(new Movie("1234",1, "POC", "good Movie", "www.abc.com", "2015-03-23", 45.5, 112,"Jhon123"));
		repo.save(new Movie("5432",2, "POC-1", "good Movie", "www.abc.com", "2015-03-23", 45.5, 112,"Jh0n123"));
		repo.save(new Movie("2345",3, "Superman", "good Movie", "www.abc.com", "2015-03-23", 45.5, 112,"Jhon123"));
		repo.save(new Movie("9876",4, "Superman-1", "good Movie", "www.abc.com", "2015-03-23", 45.5, 112,"Jhon123"));
		List<Movie> myMovies = repo.findByUserId("Jhon123");
		System.out.println("myMovies::"+ myMovies.get(0).getName());
		assertEquals("POC", myMovies.get(0).getName());
	
		
		
	}
	
}