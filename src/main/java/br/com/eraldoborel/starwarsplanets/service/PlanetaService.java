package br.com.eraldoborel.starwarsplanets.service;

import br.com.eraldoborel.starwarsplanets.model.Planeta;
import br.com.eraldoborel.starwarsplanets.service.exceptions.NomeDuplicadoException;
import br.com.eraldoborel.starwarsplanets.service.exceptions.PlanetaNaoEncontradoException;

public interface PlanetaService {

	Planeta salvar(Planeta planeta) throws NomeDuplicadoException;

	Planeta buscar_por_nome(String nome) throws PlanetaNaoEncontradoException;

}
