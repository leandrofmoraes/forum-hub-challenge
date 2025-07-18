# forum-hub-challenge
Desafio prático para criação de uma API REST

[![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)](https://openjdk.org/) - [![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)](https://spring.io/) - [![MySQL](https://img.shields.io/badge/MySQL-00000F?style=for-the-badge&logo=mysql&logoColor=white)](https://www.mysql.com/) - [![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)](https://www.docker.com/)

## ✨ Funcionalidades

## Requisitos
- Java 21+
- MySQL 8.0+
- Docker (opcional, para ambiente isolado)

## 📦 Instalação

1 - Clone o repositório:
```bash
git clone https://github.com/leandrofmoraes/forum-hub-challenge.git && cd literalura
```

2 - Crie um banco de dados MySQL ou utilize o docker-compose para criar um container:
```bash:
docker-compose --env-file .env up -d
```

3 - Antes de rodar a aplicação, configure as variáveis de ambiente:
```bash
MYSQL_USER=<seu_usuário>
MYSQL_PASSWORD=<senha>
MYSQL_DB=<nome_do_banco>
MYSQL_PORT=3306
```
4 - Execute a aplicação:
```bash
./mvnw spring-boot:run
```
