package br.com.eraldoborel.starwarsplanets.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.eraldoborel.starwarsplanets.model.Planeta;
import br.com.eraldoborel.starwarsplanets.service.PlanetaService;
import br.com.eraldoborel.starwarsplanets.service.exceptions.ApiSWIndisponivelException;
import br.com.eraldoborel.starwarsplanets.service.exceptions.NomeDuplicadoException;
import br.com.eraldoborel.starwarsplanets.service.exceptions.PlanetaNaoEncontradoException;

@RestController
@RequestMapping("/planetas")
public class PlanetaResource {
	
	@Autowired
	private PlanetaService servico;

	@RequestMapping
	public ResponseEntity<List<Planeta>> listar() throws PlanetaNaoEncontradoException {
		List<Planeta> planetas = servico.findAll();
		
		return new ResponseEntity<List<Planeta>>(planetas, HttpStatus.OK);
	}
	
	@RequestMapping("/nome/{nome}/")
	public ResponseEntity<Planeta> buscarPorNome(@PathVariable("nome") String nome) throws PlanetaNaoEncontradoException {
		Planeta planeta = servico.buscar_por_nome(nome);
		
		return new ResponseEntity<Planeta>(planeta, HttpStatus.OK);
	}
	
	@RequestMapping("/{id}/")
	public ResponseEntity<Planeta> buscarPorId(@PathVariable("id") String id) throws PlanetaNaoEncontradoException {
		Planeta planeta = servico.buscar_por_id(id);
		
		return new ResponseEntity<Planeta>(planeta, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Planeta> criar(@RequestBody Planeta planeta, HttpServletResponse response) throws NomeDuplicadoException, ApiSWIndisponivelException {
		Planeta planetaSalvo = servico.criar(planeta);
		
		URI url = ServletUriComponentsBuilder
				.fromCurrentRequestUri().path("/{id}/")
				.buildAndExpand(planetaSalvo.getId()).toUri();
		
		response.setHeader("Location", url.toASCIIString());
		
		return new ResponseEntity<Planeta>(planetaSalvo, HttpStatus.CREATED);
	}
	
	@PostMapping("/{id}/")
	public ResponseEntity<Planeta> atualizar(@PathVariable("id") String id, @RequestBody Planeta planeta, HttpServletResponse response) throws NomeDuplicadoException, PlanetaNaoEncontradoException {
		Planeta planetaSalvo = servico.atualizar(id, planeta);
		
		URI url = ServletUriComponentsBuilder
				.fromCurrentRequestUri().path("/{id}/")
				.buildAndExpand(planetaSalvo.getId()).toUri();
		
		response.setHeader("Location", url.toASCIIString());
		
		return new ResponseEntity<Planeta>(planetaSalvo, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}/")
	public ResponseEntity<Mensagem> remover(@PathVariable("id") String id) throws PlanetaNaoEncontradoException {
		
		servico.apagar(id);
		
		Mensagem mensagem = new Mensagem("Planeta com o id '" + id + "' foi removido com sucesso.");
		return new ResponseEntity<Mensagem>(mensagem  , HttpStatus.OK);
	}
	
	
	@ExceptionHandler({NomeDuplicadoException.class})
	public ResponseEntity<Erro> handleNomeDuplicadoException (NomeDuplicadoException e) {
		Erro erro = new Erro(e.getMensagem());
		return new ResponseEntity<Erro>(erro , HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler({PlanetaNaoEncontradoException.class})
	public ResponseEntity<Erro> handlePlanetaNaoEncontrado (PlanetaNaoEncontradoException e) {
		Erro erro = new Erro(e.getMensagem());
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
	
	class Mensagem {
		private final String mensagem;

		public Mensagem(String mensagem) {
			super();
			this.mensagem = mensagem;
		}

		public String getMensagem() {
			return mensagem;
		}
	}
}
