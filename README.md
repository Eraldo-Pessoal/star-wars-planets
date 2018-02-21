
# API - Planetas de Star Wars


## Tecnologias Empregadas

1. MongoDB
2. Spring Boot
3. Rest Assured
4. Maven


## Testes unitários

* Baseados em JUnit.
* Local ``src/java/java``.


## Testes de integração

* Fazem uso de um banco de dados mongoDB que é carregado em memória automaticamente. Ao fim da execução dos testes o banco é automaticamente desativado e destruído.
* Fazem uso de um server que gera um *stub* para as requisições feitas para a API SW. 
* Local ``src/it/java``.


## Aplicação

1. Instalar banco de dados mongo escutando na porta default (27017).
2. Executar a classe br.com.eraldoborel.starwarsplanets.StarWarsPlanetsApplication. Levanta um tomcat *embedado* atendendo em [http://localhost:8080](http://localhost:8080).
3. A assinatura da API esta descrita no arquivo api_planetas.yaml. Ele pode ser lido pelo [swagger.io](http://swagger.io)
