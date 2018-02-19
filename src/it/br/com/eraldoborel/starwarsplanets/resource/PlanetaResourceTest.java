package br.com.eraldoborel.starwarsplanets.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

import org.junit.Test;
import org.springframework.http.HttpStatus;

import br.com.eraldoborel.starwarsplanets.StarWarsPlanetsBaseIntegrationTests;
import br.com.eraldoborel.starwarsplanets.model.Planeta;
import io.restassured.http.ContentType;

public class PlanetaResourceTest extends StarWarsPlanetsBaseIntegrationTests {

	
	@Test
	public void procura_pessoa_pelo_nome() {
		given()
			.pathParam("nome", "terra")
			
		.get("/planetas/nome/{nome}/")
		.then()
			.log().body().and()
			.statusCode(HttpStatus.OK.value())
			.body("nome", equalTo("Terra"));
	}
	
	
	@Test
	public void nao_encontra_pessoa_pelo_nome() {
		given()
			.pathParam("nome", "terrarrr")
			
		.get("/planetas/nome/{nome}/")
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
		.post("/planetas/")
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
		Planeta terra2 = new Planeta("Terra");
		
		given()
			.request()
			.header("Accept", ContentType.ANY)
			.header("Content-type", ContentType.JSON)
			.body(terra2)
		.when()
		.post("/planetas/")
		.then()
			.log().body()
			.and()
				.statusCode(HttpStatus.BAD_REQUEST.value())
				.body("erro", equalTo("Já existe planeta cadastrado com o nome 'Terra'"));
	}
	
	@Test
	public void remover_planeta() {
		Planeta plutao = new Planeta("Plutão");
		plutao = repository.save(plutao);
		
		given()
			.pathParam("id", plutao.getId())
		.when()
		.delete("/planetas/{id}/")
		.then()
			.log().body()
			.and()
				.statusCode(HttpStatus.OK.value())
				.body("mensagem", equalTo("Planeta com o id '" + plutao.getId() + "' foi removido com sucesso."));
	}
	
	@Test
	public void erro_ao_remover_planeta_nao_encontrado() {
		given()
			.pathParam("id", "ID0")
		.when()
		.delete("/planetas/{id}/")
		.then()
			.log().body()
			.and()
			.statusCode(HttpStatus.NOT_FOUND.value())
			.body("erro", equalTo("Não existe planeta com o id 'ID0'"));
	}
	
	@Test
	public void procura_pessoa_pelo_id() {
		given()
			.pathParam("id", terra.getId())
			
		.get("/planetas/{id}/")
		.then()
			.log().body().and()
			.statusCode(HttpStatus.OK.value())
			.body("nome", equalTo("Terra"));
	}
	
	@Test
	public void erro_ao_procurar_pessoa_pelo_id_que_nao_existe() {
		given()
			.pathParam("id", "ID0")
			
		.get("/planetas/{id}/")
		.then()
			.log().body()
			.and()
			.statusCode(HttpStatus.NOT_FOUND.value())
			.body("erro", equalTo("Não existe planeta com o id 'ID0'"));
	}
	
	@Test
	public void listar_planetas() {
		given()
			
		.get("/planetas/")
		.then()
			.log().body().and()
			.statusCode(HttpStatus.OK.value())
			.body("[0].nome", equalTo("Terra"))
			.body("[1].nome", equalTo("Saturno"))
			.body("[2].nome", equalTo("Tatooine"))
			;
	}
	
	@Test
	public void atualizar_planeta() {
		Planeta terra2 = new Planeta("Terra");
		terra2.setClima("Temperado");
		
		given()
			.pathParam("id", terra.getId())
			.request()
				.header("Accept", ContentType.ANY)
				.header("Content-type", ContentType.JSON)
			.body(terra2)
		.when()
			.post("/planetas/{id}/")
		.then()
			.log().headers()
			.and()
					.log().body()
			.and()
				.statusCode(HttpStatus.OK.value())
				.header("Location", startsWith("http://localhost:" + porta + "/planetas/"))
				.body("id", equalTo(terra.getId()))
				.body("nome", equalTo("Terra"))
				.body("clima", equalTo("Temperado"));
	}
	
	//TODO: Criar teste para update de planeta que não existe
	
	//TODO: Criar teste para update gerando nome duplicado
}