package br.com.eraldoborel.starwarsplanets.service.impl;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.eraldoborel.starwarsplanets.model.apisw.Planet;
import br.com.eraldoborel.starwarsplanets.model.apisw.Result;
import br.com.eraldoborel.starwarsplanets.service.AparicoesFilmesSWService;
import br.com.eraldoborel.starwarsplanets.service.exceptions.ApiSWIndisponivelException;

@Service
@Qualifier
public class AparicoesFilmesSWServiceImpl implements AparicoesFilmesSWService {
	
	@Autowired
	private RestTemplate restTemplate;
	

	@Override
	public int getNumeroAparicoes(String nome) throws ApiSWIndisponivelException {
		try{
			Result result = restTemplate.getForObject("https://swapi.co/api/planets/?search=" + nome, Result.class);
		
			for (Planet planet: result.getResults()) {
				if (planet.getName().equalsIgnoreCase(nome)) {
					return planet.getFilms().size();
				}
			}
			
			return 0;
		} catch (Exception e) {
			throw new ApiSWIndisponivelException();
		}
	}
	
	
	@Bean
	private RestTemplate restTemplate() {
		CloseableHttpClient httpClient = HttpClients.custom().setSSLHostnameVerifier(new NoopHostnameVerifier()).build();
		HttpComponentsClientHttpRequestFactory useApacheHttpClient = new HttpComponentsClientHttpRequestFactory();
		useApacheHttpClient.setHttpClient(httpClient);
		
		return new RestTemplate(useApacheHttpClient);
	}

}
