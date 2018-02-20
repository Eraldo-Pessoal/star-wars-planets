package br.com.eraldoborel.starwarsplanets.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Test;

import br.com.eraldoborel.starwarsplanets.model.Planeta;

public class PlanetaRepositoryTests extends br.com.eraldoborel.starwarsplanets.StarWarsPlanetsBaseIntegrationTests {


    @Test (expected = Exception.class)
    public void nao_salvar_planeta_com_nome_igual() {
    	Planeta planeta = new Planeta("Terra");
    	
        repository.save(planeta);
    }
    
    
    @Test
    public void encontrar_planeta_pelo_nome() {
	    	Optional<Planeta> optional = repository.findByNomeIgnoreCase("terra");
	    	
	    	assertThat(optional.isPresent()).isTrue();
	    	assertThat(optional.get().getNome()).isEqualTo("Terra");
    }
    
    
    @Test
    public void nao_encontrar_planeta_pelo_nome() {
	    	Optional<Planeta> optional = repository.findByNomeIgnoreCase("marte");
	    	
	    	assertThat(optional.isPresent()).isFalse();
    }
}
