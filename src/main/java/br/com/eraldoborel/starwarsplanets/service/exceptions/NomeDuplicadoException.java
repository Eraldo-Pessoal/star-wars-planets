package br.com.eraldoborel.starwarsplanets.service.exceptions;

public class NomeDuplicadoException extends Exception {

	private static final long serialVersionUID = 548425026649654056L;
	
	private final String nome;

	public String getNome() {
		return nome;
	}

	public NomeDuplicadoException(String nome) {
		super();
		this.nome = nome;
	}
}
