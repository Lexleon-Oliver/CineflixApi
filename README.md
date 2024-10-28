# API do Catálogo Digital de Filmes e Séries

## Descrição do Projeto

A API do Catálogo Digital de Filmes e Séries é um programa desenvolvido em Java utilizando o framework Spring Boot e gerenciado com o Maven. Essa API tem como objetivo servir como backend para o catálogo digital de filmes e séries, o qual é alimentado por um frontend desenvolvido em Angular e executado em um container Docker.

A ideia para o desenvolvimento desse projeto surgiu quando iniciei meus estudos em frontend e criei um clone do Netflix utilizando HTML e CSS. Com o modelo frontend pronto, que considero a parte mais desafiadora, a atenção se voltou para a criação desta API em Java utilizando Spring Boot. Posteriormente, a API foi adaptada e integrada ao frontend Angular, proporcionando uma experiência completa e interativa para os usuários do catálogo digital.

## Funcionalidades da API

- Cadastrar, editar e excluir filmes e séries no catálogo.
- Consultar informações detalhadas sobre filmes e séries, como sinopse, classificação, elenco, gênero, entre outras.
- Permitir que o frontend acesse e consuma os dados da API por meio de endpoints bem definidos.

## Tecnologias Utilizadas

- Java: Linguagem de programação utilizada para desenvolver a API.
- Spring Boot: Framework para facilitar a criação de aplicações Java, fornecendo recursos e configurações pré-definidas.
- Maven: Gerenciador de dependências utilizado para facilitar a construção e o gerenciamento do projeto.
- Docker: Utilizado para empacotar a aplicação e suas dependências em um container, permitindo uma implantação consistente e portátil.

## Como Executar o Projeto

Para executar a API do Catálogo Digital de Filmes e Séries, siga os passos abaixo:

1. Certifique-se de ter o Java JDK instalado em sua máquina.
2. Instale um banco de dados postgres com o nome de cinemanager-db ou use um docker conforme explicação em `Dockerização da Aplicação`.
3. Certifique-se de salvar as variaveis de ambiente: `DATABASE_USER_NAME`, `DATABASE_USER_PASSWORD` com o login e senha do banco.
4. Clone este repositório em sua máquina local:
```
git clone https://github.com/Lexleon-Oliver/CineflixApi.git
```
5. Navegue até o diretório raiz do projeto:
```
cd CineflixApi
```
6. Execute a aplicação utilizando o Maven:
```
mvn spring-boot:run
```
7. A API estará acessível em: `http://localhost:8080/api`.

## Dockerização da Aplicação

Caso deseje executar a aplicação em um container Docker, siga os passos abaixo:

1. Certifique-se de ter o Docker instalado e configurado em sua máquina.
2. Navegue até o diretório raiz do projeto (caso ainda não esteja nele).
3. Execute o banco de dados postgres ou crie um contêiner com o seguinte comando:
```
docker run --name nome_do_docker -d -p 5432:5432 -e POSTGRES_USER=valor_da_variavel_DATABASE_USER_NAME -e POSTGRES_PASSWORD=valor_da_variavel_DATABASE_USER_PASSWORD -e POSTGRES_DB=cinemanager-db postgres

```
4. Faça build e gere o artefato
```
mvn clean install
```
5. Execute o artefato gerado:

```bash
java --enable-preview -jar target/cineflix-0.0.1-SNAPSHOT.jar
```

6. Crie a imagem Docker da aplicação com o seguinte comando:

```bash
docker build -t nome_da_sua_imagem:tag .
```

7. Após a conclusão da construção da imagem, execute o contêiner com o seguinte comando:

```bash
docker run --name nome_do_docker -p 8080:8080 -d -e DATABASE_SERVER_NAME=nome_docker_banco_de_dados -e DATABASE_USER_NAME=valor_da_variavel_POSTGRES_USER -e DATABASE_USER_PASSWORD=valor_da_variavel_POSTGRES_USER --link nome_docker_banco_de_dados:nome_docker_banco_de_dados nome_da_sua_imagem:tag 

Lembre-se de substituir "nome_da_sua_imagem", "tag", "nome_do_seu_container" e a porta (caso deseje utilizar outra) de acordo com suas preferências.

## Contribuição

Contribuições são bem-vindas! Caso queira colaborar com o projeto, sinta-se à vontade para abrir um Pull Request explicando suas alterações.

## Endpoints

A API está documentada com o SWAGGER e seus endpoints podem ser consultados. Com a api em execução acesse:
`http://localhost:8080/api/swagger-ui/index.html`

## Aviso Legal

O Cineflix Api é um projeto de estudos e não possui qualquer associação oficial com a marca Netflix. Todos os dados de filmes e séries são fictícios e utilizados apenas para fins educacionais.