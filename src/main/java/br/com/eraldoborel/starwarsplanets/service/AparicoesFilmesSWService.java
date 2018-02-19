package br.com.eraldoborel.starwarsplanets.service;

import br.com.eraldoborel.starwarsplanets.service.exceptions.ApiSWIndisponivelException;

public interface AparicoesFilmesSWService {

	int getNumeroAparicoes(String string) throws ApiSWIndisponivelException;

}
