package br.com.eraldoborel.starwarsplanets.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import br.com.eraldoborel.starwarsplanets.model.apisw.Planet;
import br.com.eraldoborel.starwarsplanets.model.apisw.Result;
import br.com.eraldoborel.starwarsplanets.service.exceptions.ApiSWIndisponivelException;
import br.com.eraldoborel.starwarsplanets.service.impl.AparicoesFilmesSWServiceImpl;

@RunWith(SpringRunner.class)
public class AparicoesFilmesSWServiceTest {
	
	private AparicoesFilmesSWService aparicoesFilmesSWService;
	
	@MockBean
	private RestTemplate restTemplate;
	
	private Result result;
	
	private Planet tatooine;
	
	
	@Before
	public void setUp() throws Exception {
		aparicoesFilmesSWService = new AparicoesFilmesSWServiceImpl(restTemplate);
		
		tatooine = new Planet();
		tatooine.setName("Tatooine");
		tatooine.setFilms(Arrays.asList(
			"https://swapi.co/api/films/5/", 
	        "https://swapi.co/api/films/4/", 
	        "https://swapi.co/api/films/6/", 
	        "https://swapi.co/api/films/3/", 
	        "https://swapi.co/api/films/1/"
        ));
		
		result = new Result();
		result.setResults(Arrays.asList(tatooine));

		when(restTemplate.getForObject(Mockito.anyString(), Mockito.any())).thenReturn(result);
	}
	
	
	@Test
	public void obtem_quantidade_aparicoes_de_um_planeta() throws Exception {
		int quantidade = aparicoesFilmesSWService.getNumeroAparicoes("tatooine");
		
		assertThat(quantidade).isEqualTo(5);
		verify(restTemplate).getForObject("https://swapi.co/api/planets/?search=tatooine", Result.class);
	}
	
	@Test
	public void obtem_quantidade_aparicoes_de_um_planeta_quando_retorna_uma_lista() throws Exception {
		Planet terra = new Planet();
		terra.setName("Terra");
		terra.setFilms(Arrays.asList("https://swapi.co/api/films/1/"));
		
		result.setResults(Arrays.asList(terra , tatooine));
		
		int quantidade = aparicoesFilmesSWService.getNumeroAparicoes("tatooine");
		
		assertThat(quantidade).isEqualTo(5);
		verify(restTemplate).getForObject("https://swapi.co/api/planets/?search=tatooine", Result.class);
	}
	
	@Test
	public void obtem_quantidade_zero_de_aparicoes_caso_nao_exista_no_cadastro() throws Exception {
		result.setResults(new ArrayList<Planet>());
		
		when(restTemplate.getForObject(Mockito.anyString(), Mockito.any())).thenReturn(result);
		
		int quantidade = aparicoesFilmesSWService.getNumeroAparicoes("tatooine2");
		
		assertThat(quantidade).isEqualTo(0);
		verify(restTemplate).getForObject("https://swapi.co/api/planets/?search=tatooine2", Result.class);
	}
	
	@Test(expected=ApiSWIndisponivelException.class)
	public void dispara_erro_quando_api_esta_fora() throws Exception {
		when(restTemplate.getForObject(Mockito.anyString(), Mockito.any())).thenThrow(new ResourceAccessException(null));
		
		aparicoesFilmesSWService.getNumeroAparicoes("tatooine");
	}
}
