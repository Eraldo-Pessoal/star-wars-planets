package br.com.eraldoborel.starwarsplanets.service;

import br.com.eraldoborel.starwarsplanets.model.Planeta;
import br.com.eraldoborel.starwarsplanets.service.exceptions.NomeDuplicadoException;

public interface PlanetaService {

	Planeta salvar(Planeta planeta) throws NomeDuplicadoException;

}
