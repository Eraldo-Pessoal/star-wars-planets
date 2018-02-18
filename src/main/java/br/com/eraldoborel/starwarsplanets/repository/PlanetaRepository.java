package br.com.eraldoborel.starwarsplanets.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.eraldoborel.starwarsplanets.model.Planeta;

public interface PlanetaRepository extends MongoRepository<Planeta, String> {

	Optional<Planeta> findByNomeIgnoreCase(String nome);

}
