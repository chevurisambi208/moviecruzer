package com.ashok.moviecruiserapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ashok.moviecruiserapp.domain.Movie;



public interface MovieRepository extends JpaRepository<Movie, Integer> {

	//@Query
	List<Movie> findByUserId(String userId);
}
