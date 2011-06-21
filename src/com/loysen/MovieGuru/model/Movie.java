package com.loysen.MovieGuru.model;

import java.io.Serializable;

public class Movie implements Serializable{
	
	private long id;
	private String title;
	private long year;
	//private double runtime;
	private String synopsis;
	private Ratings ratings;
	private Posters posters;
	
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
//	public double getRuntime() {
//		return runtime;
//	}
//	public void setRuntime(double runtime) {
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
	
}
