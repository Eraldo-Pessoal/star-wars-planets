package br.com.eraldoborel.starwarsplanets.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;
import org.springframework.http.HttpStatus;

import br.com.eraldoborel.starwarsplanets.StarWarsPlanetsApplicationTests;

public class PlanetaResourceTest extends StarWarsPlanetsApplicationTests {

	
	@Test
	public void procura_pessoa_pelo_nome() {
		given()
			.pathParam("nome", "terra")
			
		.get("/planetas/{nome}")
		.then()
			.log().body().and()
			.statusCode(HttpStatus.OK.value())
			.body("nome", equalTo("Terra"));
	}
	
}
