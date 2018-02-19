package br.com.eraldoborel.starwarsplanets;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.eraldoborel.starwarsplanets.model.Planeta;
import br.com.eraldoborel.starwarsplanets.repository.PlanetaRepository;
import io.restassured.RestAssured;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class StarWarsPlanetsApplicationTests {
	
	@Value("${local.server.port}")
	protected int porta;

	
	@Autowired
	protected PlanetaRepository repository;

    protected Planeta terra, saturno, tatooine;

    @Before
    public void setUp() {
    	RestAssured.port = porta;
    	
        repository.deleteAll();

        terra = new Planeta("Terra");
        saturno = new Planeta("Saturno");
        tatooine = new Planeta("Tatooine");
        
		repository.save(Arrays.asList(terra, saturno, tatooine));
    }
    
	@Test
	public void contextLoads() {
		System.out.println(porta);
	}

}
