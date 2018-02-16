package br.com.eraldoborel.starwarsplanets.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.eraldoborel.starwarsplanets.model.Planeta;
import br.com.eraldoborel.starwarsplanets.repository.PlanetaRepository;
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
	}
	
	@Test
	public void salvar_planeta() throws Exception {
		servico.salvar(planeta);
		
		Mockito.verify(repository).save(planeta);
	}
}
