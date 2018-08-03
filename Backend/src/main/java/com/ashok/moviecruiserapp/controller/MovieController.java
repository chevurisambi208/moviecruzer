package com.ashok.moviecruiserapp.controller;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ashok.moviecruiserapp.domain.Movie;
import com.ashok.moviecruiserapp.exception.MovieAlreadyExistsException;
import com.ashok.moviecruiserapp.exception.MovieNotFoundException;
import com.ashok.moviecruiserapp.service.MovieService;

import io.jsonwebtoken.Jwts;
@CrossOrigin("*")
@RestController
@RequestMapping(path="/api/v1/movieservice")
public class MovieController {
	
	@Autowired
	private MovieService movieService;
	
	
	@PostMapping("/movie")
	public ResponseEntity<?> saveNewMovie(@RequestBody Movie movie,HttpServletRequest request,HttpServletRequest response){
		System.out.println("saving movie");
		final String authHeader = request.getHeader("authorization");
		final String token = authHeader.substring(7);
		String userId = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody().getSubject();
		System.out.println("userId::"+userId);
		try {
			movie.setUserId(userId);
		 movieService.saveMovie(movie);
		 } catch (MovieAlreadyExistsException e) {
			return new ResponseEntity<String> (e.getMessage(),HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Movie> (movie,HttpStatus.OK);
	}
	
	@PutMapping(path="/movie/{id}")
	public ResponseEntity<?> updateMovie(@PathVariable ("id") Integer id, @RequestBody Movie movie){
	   Movie fetchedMovie;
		try {
	 fetchedMovie=movieService.updateMovie(id,movie);
		} catch (MovieNotFoundException e) {
			return new ResponseEntity<String> (e.getMessage(),HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Movie> (movie,HttpStatus.OK);
	}
	
	@DeleteMapping(value="/movie/{id}")
	public ResponseEntity<?> deleteMovieById(@PathVariable("id") int id){
		try {
		 movieService.deleteMovieById(id);
		} catch (MovieNotFoundException e) {
			return new ResponseEntity<String> (e.getMessage(),HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity <String> ("movie deleted successfully",HttpStatus.OK);
	}
	
	@GetMapping(path="/movie/{id}")
	public ResponseEntity<?> fetchMovieById(@PathVariable("id") int id){
		Movie thisMovie;
		try {
		 thisMovie=movieService.getMovieById(id);
		} catch (MovieNotFoundException e) {
			return new ResponseEntity<String> (e.getMessage(),HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity <Movie> (thisMovie,HttpStatus.OK);
	}
	
     @GetMapping(path = "/movies")
 	public @ResponseBody ResponseEntity<List<Movie>> fetchMyMovies(final ServletRequest req,
 			final ServletResponse res) {

 		final HttpServletRequest request = (HttpServletRequest) req;
 		final String authHeader = request.getHeader("authorization");
 		final String token = authHeader.substring(7);
 		String userId = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody().getSubject();
 		System.out.println("userId::"+userId);
 		return new ResponseEntity<List<Movie>>(movieService.getMyMovies(userId), HttpStatus.OK);
 	}
}
