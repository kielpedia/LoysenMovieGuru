package com.loysen.MovieGuru.model;

import java.io.Serializable;

public class Ratings implements Serializable{

	private int critics_score;
	private int audience_score;
	
	public int getCritics_score() {
		return critics_score;
	}
	public void setCritics_score(int critics_score) {
		this.critics_score = critics_score;
	}
	public int getAudience_score() {
		return audience_score;
	}
	public void setAudience_score(int audience_score) {
		this.audience_score = audience_score;
	}
	
	
}
