## 🚀 GoTiny — Encurtador de URLs com QR Code

GoTiny é uma aplicação backend desenvolvida em **Spring Boot** que permite encurtar URLs longas, redirecionar usuários automaticamente e gerar **QR Codes** para facilitar o compartilhamento.

Este projeto foi criado com foco em boas práticas de arquitetura, organização de código e aprendizado de conceitos modernos de desenvolvimento backend.

#### LINK PARA O FRONTEND: https://github.com/MarcelFeo/gotiny_front

---

## 📌 Funcionalidades

* 🔗 Encurtar URLs longas
* 🔀 Redirecionamento automático para a URL original
* 📷 Geração de QR Code para URLs encurtadas
* 🗄️  Persistência em banco de dados PostgreSQL
* 🧩 Estrutura baseada em Service, Controller e Repository

---

## 🛠️ Tecnologias Utilizadas

* **Java 17+**
* **Spring Boot**
* Spring Web
* Spring Data JPA
* PostgreSQL
* Supabase
* Hibernate
* ZXing (QR Code Generator)
* Gradle

---

## 📂 Estrutura do Projeto

```
GoTiny
 ┣ 📂 controller
 ┣ 📂 service
 ┣ 📂 repository
 ┣ 📂 model
 ┣ 📂 dto
 ┣ 📂 config
 ┗ GoTinyApplication.java
```

---

## ⚙️ Configuração do Ambiente

### 1️⃣ Clone o repositório

```bash
git clone https://github.com/MarcelFeo/GoTiny.git
cd GoTiny
```

### 2️⃣ Configure o banco de dados PostgreSQL

### 3️⃣ Configure o `application.properties`

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/gotiny
spring.datasource.username=postgres
spring.datasource.password=senha

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 4️⃣ Execute a aplicação

A aplicação estará disponível em:

```
http://localhost:8081
```

---

## 🔥 Endpoints da API

### 📌 Encurtar URL

```http
POST /gotiny
```

#### Body (JSON)

```json
{
  "url": "https://www.linkedin.com/in/marcelfeo/"
}
```

#### Response

```json
{
  "id": "UUID",
  "urlLong": "https://www.linkedin.com/in/marcelfeo/",
  "urlShort": "http://localhost:8081/r/LD6sm",
  "urlQrCode": "BASE64_QR_CODE",
  "urlCreated_at": "2026-02-05T17:25:47"
}
```

---

### 🔀 Redirecionar para URL Original

```http
GET /r/{urlShort}
```

Exemplo:

```
http://localhost:8081/r/LD6sm
```

---

---

## 🧠 Arquitetura

A aplicação segue os princípios:

* **SOLID**
* **Separação de responsabilidades**
* **Camadas bem definidas (Controller, Service, Repository)**
* **DTOs para comunicação com a API**

---

## 🤝 Contribuição

Contribuições são bem-vindas!

1. Faça um fork do projeto
2. Crie uma branch (`git checkout -b feature/minha-feature`)
3. Commit suas alterações (`git commit -m 'feat: nova feature'`)
4. Push para a branch (`git push origin feature/minha-feature`)
5. Abra um Pull Request

---

## 👨‍💻 Autor

**Marcel**
Estudante de Ciência da Computação e Desenvolvedor Backend Java

* LinkedIn: [https://www.linkedin.com/in/marcelfeo/](https://www.linkedin.com/in/marcelfeo/)
* GitHub: [https://github.com/MarcelFeo](/GoTiny.git)

---

⭐ Se você gostou do projeto, deixe uma estrela no repositório!
