package br.com.eraldoborel.starwarsplanets.model.apisw;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Result {
	private List<Planet> results;
	
	public Result(List<Planet> results) {
		super();
		this.results = results;
	}

	public Result() {
		super();
	}

	public List<Planet> getResults() {
		return results;
	}

	public void setResults(List<Planet> results) {
		this.results = results;
	}
}