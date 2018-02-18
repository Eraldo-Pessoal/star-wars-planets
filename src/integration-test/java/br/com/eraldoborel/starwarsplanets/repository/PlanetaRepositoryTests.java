package br.com.eraldoborel.starwarsplanets.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.eraldoborel.starwarsplanets.model.Planeta;
import br.com.eraldoborel.starwarsplanets.repository.PlanetaRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataMongoTest
@TestPropertySource("classpath:application-test.properties")
public class PlanetaRepositoryTests {

    @Autowired
    PlanetaRepository repository;

    private Planeta terra, saturno, tatooine;

    @Before
    public void setUp() {
        repository.deleteAll();

        terra = new Planeta("Terra");
        saturno = new Planeta("Saturno");
        tatooine = new Planeta("Tatooine");
        
		repository.save(Arrays.asList(terra, saturno, tatooine));
    }

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
