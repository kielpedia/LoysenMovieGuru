package com.loysen.MovieGuru.model;

import java.io.Serializable;
import java.util.List;

public class Movie implements Serializable{
	
	private long id;
	private String title;
	private long year;
//	private Integer runtime;
	private String synopsis;
	private Ratings ratings;
	private Posters posters;
	private List<Cast> abridged_cast;
	private List<Cast> abridged_directors;
	private List<String> genres;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public long getYear() {
		return year;
	}
	public void setYear(long year) {
		this.year = year;
	}
//	public Integer getRuntime() {
//		return runtime;
//	}
//	public void setRuntime(Integer runtime) {
//		this.runtime = runtime;
//	}
	public String getSynopsis() {
		return synopsis;
	}
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	public Ratings getRatings() {
		return ratings;
	}
	public void setRatings(Ratings ratings) {
		this.ratings = ratings;
	}
	public Posters getPosters() {
		return posters;
	}
	public void setPosters(Posters posters) {
		this.posters = posters;
	}
	public void setAbridged_cast(List<Cast> abridged_cast) {
		this.abridged_cast = abridged_cast;
	}
	public List<Cast> getAbridged_cast() {
		return abridged_cast;
	}
	public void setAbridged_directors(List<Cast> abridged_directors) {
		this.abridged_directors = abridged_directors;
	}
	public List<Cast> getAbridged_directors() {
		return abridged_directors;
	}
	public void setGenres(List<String> genres) {
		this.genres = genres;
	}
	public List<String> getGenres() {
		return genres;
	}
	
}
