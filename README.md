# NPVet - Sistema de Gestão de Hospital Veterinário 🐾

Bem-vindo ao repositório do projeto NPVet! Este projeto consiste em uma API Spring Boot integrada com um banco de dados PostgreSQL, desenvolvida para um sistema de gestão de hospital veterinário. Aqui estão as instruções para configurar o ambiente de desenvolvimento e implantação em produção.

## Tecnologias Utilizadas 🛠️

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![Postman](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white)

## Configuração do Ambiente de Desenvolvimento 🚀

Para configurar o ambiente de desenvolvimento, siga os passos abaixo:

1. Clone este repositório para o seu computador:

   ```bash
   git clone https://github.com/seu-usuario/npvet-backend.git

2. Crie um arquivo .env na raiz do projeto e configure-o com informações semelhantes a estas:
   ```
   API_PORT=8080     # Porta da API em Java
   DB_HOST=localhost  # Host do banco de dados
   DB_PORT=5432       # Porta do banco de dados
   DB_NOME=npvet      # Nome do banco de dados
   DB_USER=postgres   # Usuário do banco de dados
   DB_PWD=postgres    # Senha do banco de dados
   DDL=create-drop    # Tipo de interação com o banco de dados (create, create-drop, validate, update)
   ```
3. Use sua IDE de preferência e faça o RUN da main.
4. Acesse a API em http://localhost:8080.

## Implantação em Produção 🌐
Para implantar o projeto em produção, você pode simplesmente puxar a imagem Docker do GitHub Packages usando o seguinte comando:
```
docker pull ghcr.io/orbitechz/npvet-backend-cloud:main
```

Depois de puxar a imagem, você pode executá-la em seu ambiente de produção usando o Docker ou a plataforma de orquestração de contêiner de sua escolha.
