# vr-challenge

Requerimentos de sistema
- Java 8
- Maven 3.3

Como rodar testes (todos os testes nesse projeto são unitários não tendo nenhuma dependência externa)
- mvn clean test

Como iniciar o build
- mvn clean install

Como rodar a aplicação pelo Maven (a aplicação irá rodar na porta 8080 por padrão e irá carregar em memória os dados das "Provincias" do json fornecido)
- mvn spring-boot:run

End-points

- Post: /properties
Response 201: em caso de sucesso.
Response 400: em caso de dados inválidos no body.
Response 409: em caso de já existir uma "Property" na cordenada solicitada.

- GET: /properties/{id}
Response 200: em caso de resource encontrado.
Response 404: em caso de resource não encontrado.

- GET /properties?ax={integer}&ay={integer}&bx={integer}&by={integer}
Response 200: encontrando ou não "Properties" nas cordenadas (caso não encontre a coleção retorna vazia).
