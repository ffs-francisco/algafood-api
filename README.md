[![Build Status](https://travis-ci.com/fsilvafrancisco16/algafood-api.svg?branch=master)](https://travis-ci.com/fsilvafrancisco16/algafood-api)

# API AlgaFood

 Projeto criado para o treinamento Especialista Spring REST na AlgaWorks


## Banco de Dados
#### Mysql
 Para configurar um banco de dados Mysql em um containner docker.

```
docker run --name algafood-mysql -e MYSQL_ROOT_PASSWORD=admin -e MYSQL_DATABASE=algafood -e MYSQL_USER=algafood -e MYSQL_PASSWORD=algafood -p 3306:3306 -d mysql:5.7
```
#### Flyway
 Para reparar migrações com erro. No diretorio do projeto executar o comando abaixo pelo terminal;

```
 mvn flyway:repair -Dflyway.configFiles=src/main/resources/flyway.properties
```


## Testes
 Na execução dos testes são estartados servidores embutidos para testes de integração com serviços externos à aplicação.
 Necessário autorizar a aplicação a baixar e configurar essas dependências no sistema operacional
##### no Linux
```sudo apt-get install libaio1```