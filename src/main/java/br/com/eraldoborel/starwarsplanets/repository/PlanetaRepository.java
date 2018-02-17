package br.com.eraldoborel.starwarsplanets.repository;

import java.util.Optional;

import br.com.eraldoborel.starwarsplanets.model.Planeta;

public interface PlanetaRepository {

	Planeta save(Planeta planeta);

	Optional<Planeta> findByNome(String nome);

}
