package br.com.eraldoborel.starwarsplanets.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.eraldoborel.starwarsplanets.model.Planeta;
import br.com.eraldoborel.starwarsplanets.repository.PlanetaRepository;
import br.com.eraldoborel.starwarsplanets.service.exceptions.NomeDuplicadoException;
import br.com.eraldoborel.starwarsplanets.service.exceptions.PlanetaNaoEncontradoException;
import br.com.eraldoborel.starwarsplanets.service.impl.PlanetaServiceImpl;

@RunWith(SpringRunner.class)
public class PlanetaServiceTest {
	
	@MockBean
	private PlanetaRepository repository;
	
	@MockBean
	private AparicoesFilmesSWService aparicoesFilmesSWService;
	
	private PlanetaService servico;
	
	private Planeta planeta;
	
	@Before
	public void setUp() throws Exception {
		servico = new PlanetaServiceImpl(repository, aparicoesFilmesSWService);
		
		planeta = new Planeta("Terra");
		
		when(repository.findByNomeIgnoreCase(planeta.getNome())).thenReturn(Optional.empty());
	}
	
	@Test
	public void salvar_planeta() throws Exception {
		servico.criar(planeta);
		
		verify(repository).save(planeta);
	}
	
	@Test(expected = NomeDuplicadoException.class)
	public void nao_salvar_dois_planetas_com_mesmo_nome() throws Exception {
		when(repository.findByNomeIgnoreCase(planeta.getNome())).thenReturn(Optional.of(planeta));
		
		servico.criar(planeta);
		
		verify(repository, never()).save(planeta);
	}
	
	@Test
	public void retorna_planeta_pelo_nome() throws Exception {
		when(repository.findByNomeIgnoreCase(planeta.getNome())).thenReturn(Optional.of(planeta));
		
		Planeta planeta_encontrado = servico.buscar_por_nome(planeta.getNome());
		
		assertEquals(planeta.getNome(), planeta_encontrado.getNome());
	}
	
	@Test(expected = PlanetaNaoEncontradoException.class)
	public void dispara_erro_quando_nao_encontra_planeta_pelo_nome() throws Exception {
		servico.buscar_por_nome(planeta.getNome());
	}
}
