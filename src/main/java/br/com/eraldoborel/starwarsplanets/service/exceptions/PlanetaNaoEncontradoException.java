package br.com.eraldoborel.starwarsplanets.service.exceptions;

public class PlanetaNaoEncontradoException extends Exception {

	private static final long serialVersionUID = 4009468952936055486L;

	private final String nome;
	
	public PlanetaNaoEncontradoException(String nome) {
		super();
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

}
