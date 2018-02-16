package br.com.eraldoborel.starwarsplanets.service;

import org.junit.Before;

import br.com.eraldoborel.starwarsplanets.service.impl.PlanetaServiceImpl;

public class PlanetaServiceTest {
	
	private PlanetaService servico;
	
	@Before
	public void setUp() throws Exception {
		servico =  new PlanetaServiceImpl();
	}
	

}
