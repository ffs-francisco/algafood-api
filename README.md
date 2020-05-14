# API AlgaFood

Projeto criado para o treinamento Especialista Spring REST na AlgaWorks

### Banco Mysql
```
docker run --name algafood-mysql -e MYSQL_ROOT_PASSWORD=admin -e MYSQL_DATABASE=algafood -e MYSQL_USER=algafood -e MYSQL_PASSWORD=algafood -p 3306:3306 -d mysql:5.7
```
### Flyway
Para reparar migrações com erro. No diretorio do projeto executar o comando abaixo pelo terminal;

```
 mvn flyway:repair -Dflyway.configFiles=src/main/resources/flyway.properties
```