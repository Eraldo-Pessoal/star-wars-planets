package br.com.eraldoborel.starwarsplanets.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eraldoborel.starwarsplanets.model.Planeta;
import br.com.eraldoborel.starwarsplanets.repository.PlanetaRepository;
import br.com.eraldoborel.starwarsplanets.service.AparicoesFilmesSWService;
import br.com.eraldoborel.starwarsplanets.service.PlanetaService;
import br.com.eraldoborel.starwarsplanets.service.exceptions.ApiSWIndisponivelException;
import br.com.eraldoborel.starwarsplanets.service.exceptions.NomeDuplicadoException;
import br.com.eraldoborel.starwarsplanets.service.exceptions.PlanetaNaoEncontradoException;

@Service
public class PlanetaServiceImpl implements PlanetaService {

	@Autowired
	private PlanetaRepository repository;
		
	@Autowired
	private AparicoesFilmesSWService aparicoesFilmesSWService;
	
	public PlanetaServiceImpl() {
		super();
	}

	public PlanetaServiceImpl(PlanetaRepository repository, AparicoesFilmesSWService aparicoesFilmesSWService) {
		this.repository = repository;
		this.aparicoesFilmesSWService = aparicoesFilmesSWService;
	}

	@Override
	public Planeta criar(Planeta planeta) throws NomeDuplicadoException, ApiSWIndisponivelException {
		
		Optional<Planeta> resultado = repository.findByNomeIgnoreCase(planeta.getNome());
		
		if (resultado.isPresent()) {
			throw new NomeDuplicadoException("Já existe planeta cadastrado com o nome '" + planeta.getNome() + "'");
		}
		
		int qtde_aparicoes = aparicoesFilmesSWService.getNumeroAparicoes(planeta.getNome());
		
		planeta.setQuantidadeAparicoesFilmes(qtde_aparicoes);
		
		return repository.save(planeta);
	}

	@Override
	public Planeta buscar_por_nome(String nome) throws PlanetaNaoEncontradoException {
		Optional<Planeta> resultado = repository.findByNomeIgnoreCase(nome);

		return resultado.orElseThrow(() -> new PlanetaNaoEncontradoException("Não existe planeta com o nome '" + nome + "'"));
	}

	@Override
	public void apagar(String id) throws PlanetaNaoEncontradoException {
		buscar_por_id(id);
		repository.delete(id);
	}

	@Override
	public Planeta buscar_por_id(String id) throws PlanetaNaoEncontradoException {
		Optional<Planeta> resultado = repository.findOptionalById(id);
		
		return resultado.orElseThrow(() -> new PlanetaNaoEncontradoException("Não existe planeta com o id '" + id + "'"));
	}

	@Override
	public List<Planeta> findAll() {
		return repository.findAll();
	}

	@Override
	public Planeta atualizar(Planeta planeta) throws PlanetaNaoEncontradoException, NomeDuplicadoException, ApiSWIndisponivelException {
		String id = planeta.getId();
		Planeta planeta_existente = buscar_por_id(id );
		
		Optional<Planeta> resultado = repository.findByNomeIgnoreCaseAndIdNot(planeta.getNome(), id);
		
		if (resultado.isPresent()) {
			throw new NomeDuplicadoException("Já existe planeta cadastrado com o nome '" + planeta.getNome() + "'");
		}
		
		int qtde_aparicoes = aparicoesFilmesSWService.getNumeroAparicoes(planeta.getNome());
		
		planeta_existente.setNome(planeta.getNome());
		planeta_existente.setClima(planeta.getClima());
		planeta_existente.setTerreno(planeta.getTerreno());
		planeta_existente.setQuantidadeAparicoesFilmes(qtde_aparicoes);
		
		return repository.save(planeta_existente);
	}
}
