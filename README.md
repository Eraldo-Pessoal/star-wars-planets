
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
2. Executar a classe br.com.eraldoborel.starwarsplanets.StarWarsPlanetsApplication.
3. Em [http://localhost:8080](http://localhost:8080) temos uma página que descreve a assinatura da API. Esta página foi gerada automaticamente a partir do arquivo *api_planetas.yaml* na plataforma [swagger.io](http://swagger.io).
