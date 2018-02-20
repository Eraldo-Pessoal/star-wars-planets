package br.com.eraldoborel.starwarsplanets.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "planeta")
public class Planeta {
	
	public Planeta() {
	
	}

	public Planeta(String nome) {
		this.nome = nome;
	}

	@Id
    private String id;
	
	@Indexed(unique = true)
	private String nome;
	
	private String clima;
	
	private String terreno;
	
	private int quantidadeAparicoesFilmes = 0;

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getClima() {
		return clima;
	}

	public void setClima(String clima) {
		this.clima = clima;
	}

	public String getTerreno() {
		return terreno;
	}

	public void setTerreno(String terreno) {
		this.terreno = terreno;
	}

	public int getQuantidadeAparicoesFilmes() {
		return quantidadeAparicoesFilmes;
	}

	public void setQuantidadeAparicoesFilmes(int quantidadeAparicoesFilmes) {
		this.quantidadeAparicoesFilmes = quantidadeAparicoesFilmes;
	}
}
