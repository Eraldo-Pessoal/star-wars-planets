package br.com.eraldoborel.starwarsplanets.service.impl;

import java.util.List;
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
			throw new NomeDuplicadoException("Já existe planeta cadastrado com o nome '" + planeta.getNome() + "'");
		}
		
		return repository.save(planeta);
	}

	@Override
	public Planeta buscar_por_nome(String nome) throws PlanetaNaoEncontradoException {
		Optional<Planeta> resultado = repository.findByNomeIgnoreCase(nome);

		return resultado.orElseThrow(() -> new PlanetaNaoEncontradoException("Não existe planeta com o nome '" + nome + "'"));
	}

	@Override
	public void apagar(String id) throws PlanetaNaoEncontradoException {
		
		Planeta planeta = repository.findOne(id);
		
		if (planeta == null) {
			throw new PlanetaNaoEncontradoException("Não existe planeta com o id '" + id + "'");
		}
		
		repository.delete(id);
	}

	@Override
	public Planeta buscar_por_id(String id) throws PlanetaNaoEncontradoException {
		Planeta planeta = repository.findOne(id);
		
		if (planeta == null) {
			throw new PlanetaNaoEncontradoException("Não existe planeta com o id '" + id + "'");
		}
		
		return planeta;
	}

	@Override
	public List<Planeta> findAll() {
		return repository.findAll();
	}

	@Override
	public Planeta atualizar(String id, Planeta planeta) {
		Planeta planeta_existente = repository.findOne(id);
		
		planeta_existente.setNome(planeta.getNome());
		planeta_existente.setClima(planeta.getClima());
		planeta_existente.setTerreno(planeta.getTerreno());
		
		planeta_existente = repository.save(planeta_existente);
		
		return planeta_existente;
	}
}
