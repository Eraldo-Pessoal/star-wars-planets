package br.com.eraldoborel.starwarsplanets.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

import org.junit.Test;
import org.springframework.http.HttpStatus;

import br.com.eraldoborel.starwarsplanets.StarWarsPlanetsApplicationTests;
import br.com.eraldoborel.starwarsplanets.model.Planeta;
import io.restassured.http.ContentType;

public class PlanetaResourceTest extends StarWarsPlanetsApplicationTests {

	
	@Test
	public void procura_pessoa_pelo_nome() {
		given()
			.pathParam("nome", "terra")
			
		.get("/planetas/nome/{nome}")
		.then()
			.log().body().and()
			.statusCode(HttpStatus.OK.value())
			.body("nome", equalTo("Terra"));
	}
	
	
	@Test
	public void nao_encontra_pessoa_pelo_nome() {
		given()
			.pathParam("nome", "terrarrr")
			
		.get("/planetas/nome/{nome}")
		.then()
			.log().body().and()
			.statusCode(HttpStatus.NOT_FOUND.value())
			.body("erro", equalTo("Não existe planeta com o nome 'terrarrr'"));
	}
	
	@Test
	public void salva_planeta() {
		Planeta alderaan = new Planeta("Alderaan");
		
		given()
			.request()
			.header("Accept", ContentType.ANY)
			.header("Content-type", ContentType.JSON)
			.body(alderaan)
		.when()
		.post("/planetas")
		.then()
			.log().headers()
			.and()
			.log().body()
			.and()
				.statusCode(HttpStatus.CREATED.value())
				.header("Location", startsWith("http://localhost:" + porta + "/planetas/"))
				.body("nome", equalTo("Alderaan"));
	}
	
	@Test
	public void nao_salva_planeta_com_nome_duplicado() {
		Planeta alderaan = new Planeta("Terra");
		
		given()
			.request()
			.header("Accept", ContentType.ANY)
			.header("Content-type", ContentType.JSON)
			.body(alderaan)
		.when()
		.post("/planetas")
		.then()
			.log().body()
			.and()
				.statusCode(HttpStatus.BAD_REQUEST.value())
				.body("erro", equalTo("Já existe planeta cadastrado com o nome 'Terra'"));
	}
}
