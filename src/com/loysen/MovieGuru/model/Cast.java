package com.loysen.MovieGuru.model;

import java.io.Serializable;
import java.util.List;

public class Cast implements Serializable{
	
	private String name;
	private List<String> characters;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getCharacters() {
		return characters;
	}
	public void setCharacters(List<String> characters) {
		this.characters = characters;
	}
	
	
}
