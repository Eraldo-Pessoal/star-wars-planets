package br.com.eraldoborel.starwarsplanets.service.exceptions;

public class PlanetaNaoEncontradoException extends Exception {

	private static final long serialVersionUID = 4009468952936055486L;

	private final String mensagem;
	
	public PlanetaNaoEncontradoException(String mensagem) {
		super();
		this.mensagem = mensagem;
	}

	public String getMensagem() {
		return mensagem;
	}

}
