API do Catálogo Digital de Filmes e Séries

Descrição do Projeto

A API do Catálogo Digital de Filmes e Séries é um programa desenvolvido em Java utilizando o framework Spring Boot e gerenciado com o Maven. Essa API tem como objetivo servir como backend para o catálogo digital de filmes e séries, o qual é alimentado por um frontend desenvolvido em Angular e executado em um container Docker.

A ideia para o desenvolvimento desse projeto surgiu quando o criador iniciou seus estudos em frontend e criou um clone do Netflix utilizando HTML e CSS. Com o modelo frontend pronto, que foi considerado a parte mais desafiadora, a atenção se voltou para a criação da API em Java utilizando Spring Boot. Posteriormente, a API foi adaptada e integrada ao frontend Angular, proporcionando uma experiência completa e interativa para os usuários do catálogo digital.
Funcionalidades da API

    Cadastrar, editar e excluir filmes e séries no catálogo.
    Consultar informações detalhadas sobre filmes e séries, como sinopse, classificação, elenco, gênero, entre outras.
    Permitir que o frontend acesse e consuma os dados da API por meio de endpoints bem definidos.

Tecnologias Utilizadas

    Java: Linguagem de programação utilizada para desenvolver a API.
    Spring Boot: Framework para facilitar a criação de aplicações Java, fornecendo recursos e configurações pré-definidas.
    Maven: Gerenciador de dependências utilizado para facilitar a construção e o gerenciamento do projeto.
    Docker: Utilizado para empacotar a aplicação e suas dependências em um container, permitindo uma implantação consistente e portátil.

Como Executar o Projeto

Para executar a API do Catálogo Digital de Filmes e Séries, siga os passos abaixo:

    Certifique-se de ter o Java JDK instalado em sua máquina.
    Clone este repositório em sua máquina local: git clone https://github.com/seu-usuario/catalogo-filmes-series.git.
    Navegue até o diretório raiz do projeto: cd catalogo-filmes-series.
    Execute a aplicação utilizando o Maven: mvn spring-boot:run.
    A API estará acessível em: http://localhost:8080 (ou outra porta definida na configuração do Spring Boot).

Dockerização da Aplicação

Caso deseje executar a aplicação em um container Docker, siga os passos abaixo:

    Certifique-se de ter o Docker instalado e configurado em sua máquina.
    Navegue até o diretório raiz do projeto (caso ainda não esteja nele).
    Crie a imagem Docker da aplicação com o seguinte comando:

bash

docker build -t nome_da_sua_imagem:tag .

    Após a conclusão da construção da imagem, execute o contêiner com o seguinte comando:

bash

docker run -d -p 8080:8080 --name nome_do_seu_container nome_da_sua_imagem:tag

Lembre-se de substituir "nome_da_sua_imagem", "tag", "nome_do_seu_container" e a porta (caso deseje utilizar outra) de acordo com suas preferências.
Contribuição

Contribuições são bem-vindas! Caso queira colaborar com o projeto, sinta-se à vontade para abrir um Pull Request explicando suas alterações.
Contato

Para entrar em contato com o desenvolvedor ou obter mais informações sobre o projeto, você pode enviar um email para catalogo.app@example.com.
