package br.com.eraldoborel.starwarsplanets;

import static org.hamcrest.CoreMatchers.startsWith;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.util.Arrays;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import br.com.eraldoborel.starwarsplanets.model.Planeta;
import br.com.eraldoborel.starwarsplanets.repository.PlanetaRepository;
import io.restassured.RestAssured;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public abstract class StarWarsPlanetsBaseIntegrationTests {

	@Value("${local.server.port}")
	protected int porta;

	@Autowired
	protected PlanetaRepository repository;
	
	@Autowired
	private RestTemplate restTemplate;

	private MockRestServiceServer mockServer;

	protected Planeta terra, saturno, tatooine;
	
	protected Resource stateFile;

	@Before
	public void setUp() {
		RestAssured.port = porta;

		repository.deleteAll();

		terra = new Planeta("Terra");
		saturno = new Planeta("Saturno");
		tatooine = new Planeta("Tatooine");

		repository.save(Arrays.asList(terra, saturno, tatooine));
		
		stateFile = new ClassPathResource("planets_result.json");
		
        mockServer = MockRestServiceServer.createServer(restTemplate);
        
        mockServer.expect(requestTo(startsWith("https://swapi.co/")))
	        .andExpect(method(HttpMethod.GET))
	        .andRespond(withSuccess(stateFile, MediaType.APPLICATION_JSON));
	}

}
