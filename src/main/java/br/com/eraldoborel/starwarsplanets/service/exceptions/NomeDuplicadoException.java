package br.com.eraldoborel.starwarsplanets.service.exceptions;

public class NomeDuplicadoException extends Exception {

	private static final long serialVersionUID = 548425026649654056L;
	
	private final String mensagem;

	public String getMensagem() {
		return mensagem;
	}

	public NomeDuplicadoException(String mensagem) {
		super();
		this.mensagem = mensagem;
	}
}
