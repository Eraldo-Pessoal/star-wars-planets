package br.com.eraldoborel.starwarsplanets.resource;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.eraldoborel.starwarsplanets.model.Planeta;
import br.com.eraldoborel.starwarsplanets.service.PlanetaService;
import br.com.eraldoborel.starwarsplanets.service.exceptions.NomeDuplicadoException;
import br.com.eraldoborel.starwarsplanets.service.exceptions.PlanetaNaoEncontradoException;

@RestController
@RequestMapping("/planetas")
public class PlanetaResource {
	
	@Autowired
	private PlanetaService servico;
	
	@RequestMapping("/nome/{nome}")
	public ResponseEntity<Planeta> buscarPorNome(@PathVariable("nome") String nome) throws PlanetaNaoEncontradoException {
		Planeta planeta = servico.buscar_por_nome(nome);
		
		return new ResponseEntity<Planeta>(planeta, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Planeta> salvar(@RequestBody Planeta planeta, HttpServletResponse response) throws NomeDuplicadoException {
		Planeta planetaSalvo = servico.salvar(planeta);
		
		URI url = ServletUriComponentsBuilder
				.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(planetaSalvo.getId()).toUri();
		
		response.setHeader("Location", url.toASCIIString());
		
		return new ResponseEntity<Planeta>(planetaSalvo, HttpStatus.CREATED);
	}
	
	@ExceptionHandler({NomeDuplicadoException.class})
	public ResponseEntity<Erro> handleNomeDuplicadoException (NomeDuplicadoException e) {
		Erro erro = new Erro("Já existe planeta cadastrado com o nome '" + e.getNome() + "'");
		return new ResponseEntity<Erro>(erro , HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler({PlanetaNaoEncontradoException.class})
	public ResponseEntity<Erro> handlePlanetaNaoEncontrado (PlanetaNaoEncontradoException e) {
		Erro erro = new Erro("Não existe planeta com o nome '" + e.getNome() + "'");
		return new ResponseEntity<Erro>(erro , HttpStatus.NOT_FOUND);
	}
	
	class Erro {
		private String erro;

		public Erro(String erro) {
			super();
			this.erro = erro;
		}

		public String getErro() {
			return erro;
		}

		public void setErro(String erro) {
			this.erro = erro;
		}
	}
}
