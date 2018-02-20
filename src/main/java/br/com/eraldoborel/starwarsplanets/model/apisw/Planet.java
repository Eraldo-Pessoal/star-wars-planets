package br.com.eraldoborel.starwarsplanets.model.apisw;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Planet {

	private String name;
	
	private List<String> films;
	
	public Planet() {
		super();
	}
	
	public Planet(String name, List<String> films) {
		super();
		this.name = name;
		this.films = films;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getFilms() {
		return films;
	}

	public void setFilms(List<String> films) {
		this.films = films;
	}
}