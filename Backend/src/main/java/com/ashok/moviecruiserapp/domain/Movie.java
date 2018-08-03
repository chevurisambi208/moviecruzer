package com.ashok.moviecruiserapp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "movie")
public class Movie {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int id;
	
	@Column(name = "movie_id")
	private String movieId;

	@Column(name = "name")
	private String name;

	@Column(name = "comments")
	private String comments;

	@Column(name = "poster_path")
	private String posterPath;

	@Column(name = "release_date")
	private String releaseDate;

	@Column(name = "vote_average")
	private Double voteAverage;

	@Column(name = "vote_count")
	private int voteCount;

	@Column(name = "user_id")
	private String userId;

	public Movie() {

	}

	public Movie(String movieId,int id, String name, String comments, String posterPath, String releaseDate, Double voteAverage,
			int voteCount, String userId) {
		super();
		this.movieId=movieId;
		this.id = id;
		this.name = name;
		this.comments = comments;
		this.posterPath = posterPath;
		this.releaseDate = releaseDate;
		this.voteAverage = voteAverage;
		this.voteCount = voteCount;
		this.userId = userId;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getPosterPath() {
		return posterPath;
	}

	public void setPosterPath(String posterPath) {
		this.posterPath = posterPath;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Double getVoteAverage() {
		return voteAverage;
	}

	public void setVoteAverage(Double voteAverage) {
		this.voteAverage = voteAverage;
	}

	public int getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(int voteCount) {
		this.voteCount = voteCount;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
