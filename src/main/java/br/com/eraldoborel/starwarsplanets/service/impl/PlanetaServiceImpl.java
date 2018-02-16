package br.com.eraldoborel.starwarsplanets.service.impl;

import br.com.eraldoborel.starwarsplanets.model.Planeta;
import br.com.eraldoborel.starwarsplanets.repository.PlanetaRepository;
import br.com.eraldoborel.starwarsplanets.service.PlanetaService;

public class PlanetaServiceImpl implements PlanetaService {

	private PlanetaRepository repository;

	public PlanetaServiceImpl(PlanetaRepository repository) {
		this.repository = repository;
	}

	@Override
	public Planeta salvar(Planeta planeta) {
		
		return repository.save(planeta);
	}

}
