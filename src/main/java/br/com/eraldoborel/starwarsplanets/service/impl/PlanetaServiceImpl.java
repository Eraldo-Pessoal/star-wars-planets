package br.com.eraldoborel.starwarsplanets.service.impl;

import java.util.Optional;

import br.com.eraldoborel.starwarsplanets.model.Planeta;
import br.com.eraldoborel.starwarsplanets.repository.PlanetaRepository;
import br.com.eraldoborel.starwarsplanets.service.PlanetaService;
import br.com.eraldoborel.starwarsplanets.service.exceptions.NomeDuplicadoException;
import br.com.eraldoborel.starwarsplanets.service.exceptions.PlanetaNaoEncontradoException;

public class PlanetaServiceImpl implements PlanetaService {

	private PlanetaRepository repository;

	public PlanetaServiceImpl(PlanetaRepository repository) {
		this.repository = repository;
	}

	@Override
	public Planeta salvar(Planeta planeta) throws NomeDuplicadoException {
		
		Optional<Planeta> resultado = repository.findByNome(planeta.getNome());
		
		if (resultado.isPresent()) {
			throw new NomeDuplicadoException();
		}
		
		return repository.save(planeta);
	}

	@Override
	public Planeta buscar_por_nome(String nome) throws PlanetaNaoEncontradoException {
		Optional<Planeta> resultado = repository.findByNome(nome);
		
		if (!resultado.isPresent()) {
			throw new PlanetaNaoEncontradoException();
		}
		
		return resultado.get();
	}

}
