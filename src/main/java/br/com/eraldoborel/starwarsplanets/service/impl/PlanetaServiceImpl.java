package br.com.eraldoborel.starwarsplanets.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eraldoborel.starwarsplanets.model.Planeta;
import br.com.eraldoborel.starwarsplanets.repository.PlanetaRepository;
import br.com.eraldoborel.starwarsplanets.service.PlanetaService;
import br.com.eraldoborel.starwarsplanets.service.exceptions.NomeDuplicadoException;
import br.com.eraldoborel.starwarsplanets.service.exceptions.PlanetaNaoEncontradoException;

@Service
public class PlanetaServiceImpl implements PlanetaService {

	@Autowired
	private PlanetaRepository repository;

	public PlanetaServiceImpl() {
		super();
	}

	public PlanetaServiceImpl(PlanetaRepository repository) {
		this.repository = repository;
	}

	@Override
	public Planeta salvar(Planeta planeta) throws NomeDuplicadoException {
		
		Optional<Planeta> resultado = repository.findByNomeIgnoreCase(planeta.getNome());
		
		if (resultado.isPresent()) {
			throw new NomeDuplicadoException(planeta.getNome());
		}
		
		return repository.save(planeta);
	}

	@Override
	public Planeta buscar_por_nome(String nome) throws PlanetaNaoEncontradoException {
		Optional<Planeta> resultado = repository.findByNomeIgnoreCase(nome);

		return resultado.orElseThrow(() -> new PlanetaNaoEncontradoException(nome));
	}

}
