package br.com.eraldoborel.starwarsplanets.service;

import java.util.List;

import br.com.eraldoborel.starwarsplanets.model.Planeta;
import br.com.eraldoborel.starwarsplanets.service.exceptions.NomeDuplicadoException;
import br.com.eraldoborel.starwarsplanets.service.exceptions.PlanetaNaoEncontradoException;

public interface PlanetaService {

	Planeta salvar(Planeta planeta) throws NomeDuplicadoException;

	Planeta buscar_por_nome(String nome) throws PlanetaNaoEncontradoException;

	void apagar(String id) throws PlanetaNaoEncontradoException;

	Planeta buscar_por_id(String id) throws PlanetaNaoEncontradoException;

	List<Planeta> findAll();

	Planeta atualizar(String id, Planeta planeta);
}
