package br.com.eraldoborel.starwarsplanets.service;

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
import br.com.eraldoborel.starwarsplanets.service.impl.PlanetaServiceImpl;

@RunWith(SpringRunner.class)
public class PlanetaServiceTest {
	
	@MockBean
	private PlanetaRepository repository;
	
	private PlanetaService servico;
	
	private Planeta planeta;
	
	@Before
	public void setUp() throws Exception {
		servico =  new PlanetaServiceImpl(repository);
		planeta = new Planeta();
		planeta.setNome("Terra");
		
		when(repository.findByNome(planeta.getNome())).thenReturn(Optional.empty());
	}
	
	@Test
	public void salvar_planeta() throws Exception {
		servico.salvar(planeta);
		
		verify(repository).save(planeta);
	}
	
	@Test(expected = NomeDuplicadoException.class)
	public void nao_salvar_dois_planetas_com_mesmo_nome() throws Exception {
		when(repository.findByNome(planeta.getNome())).thenReturn(Optional.of(planeta));
		
		servico.salvar(planeta);
		
		verify(repository, never()).save(planeta);
	}
}
