# 📌 Tasks2Do

Tasks2Do é uma aplicação web para **gestão de tarefas**, onde os usuários podem criar, atualizar e excluir tarefas. O projeto foi desenvolvido utilizando **React (TypeScript) no frontend** e **Spring Boot no backend**, com um banco de dados **MySQL** gerenciado via **Docker**.

---

## 🚀 Tecnologias Utilizadas

### **Frontend:**
- React com TypeScript
- Vite
- Axios
- React Router
- CSS Modules

### **Backend:**
- Java com Spring Boot
- Spring Security (JWT)
- JPA/Hibernate
- MySQL

### **Infraestrutura:**
- Docker & Docker Compose
- WSL2 para ambiente de desenvolvimento

---

## 🔧 Como Rodar o Projeto

### **1️⃣ Rodando com Docker (Recomendado)**

1. **Certifique-se de que o Docker e o Docker Compose estão instalados.**
2. No terminal, navegue até a pasta do projeto e execute:
   ```sh
   docker compose up --build -d
   ```
3. Acesse o frontend:
   ```sh
   http://localhost:5173
   ```
4. A API está rodando em:
   ```sh
   http://localhost:8080
   ```
5. Para parar os containers:
   ```sh
   docker compose down
   ```

### **2️⃣ Rodando Localmente (Sem Docker)**

#### **Backend:**
1. Configure um banco de dados MySQL e ajuste o `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/tasks2do
   spring.datasource.username=root
   spring.datasource.password=root
   ```
2. Rode o backend com:
   ```sh
   mvn spring-boot:run
   ```

#### **Frontend:**
1. Instale as dependências:
   ```sh
   cd frontend
   npm install
   ```
2. Rode o frontend:
   ```sh
   npm run dev
   ```

---

## 📁 Estrutura do Projeto
```
tasks2do/
├── backend/               # Código do Backend (Spring Boot)
│   ├── src/main/java/com/br/tasks2do
│   ├── src/main/resources
│   ├── Dockerfile
│   ├── pom.xml
├── frontend/              # Código do Frontend (React + TypeScript)
│   ├── src/
│   │   ├── pages/
│   │   ├── components/
│   │   ├── services/
│   │   ├── styles/
│   ├── public/
│   ├── Dockerfile
│   ├── package.json
├── docker-compose.yml     # Configuração do Docker
├── README.md              # Documentação
```

---

## 📌 Rotas da API

### **Autenticação**
| Método | Endpoint         | Descrição |
|---------|----------------|-------------|
| POST    | `/login`   | Login e geração de token JWT |
| POST    | `/cadastro`     | Cadastro de novo usuário |

### **Tarefas**
| Método | Endpoint                  | Descrição |
|---------|---------------------------|-------------|
| GET     | `/tarefas/minhas_tarefas`  | Lista todas as tarefas do usuário |
| POST    | `/tarefas/nova_tarefa`     | Cria uma nova tarefa |
| PUT     | `/tarefas/atualizar_tarefa/{id}` | Atualiza uma tarefa |
| DELETE  | `/tarefas/excluir/{id}`    | Exclui uma tarefa |

**📌 Importante:** Para acessar as rotas protegidas, é necessário enviar o token JWT no cabeçalho da requisição:
```
Authorization: Bearer <seu_token>
```

---

## 🎯 Melhorias Futuras
- Implementar testes automatizados (JUnit, Jest)
- Melhorar responsividade no frontend
- Criar um painel de dashboard com estatísticas das tarefas
- Implementar o deploy automatizado (CI/CD)

---

## 🏆 Contribuição
Se quiser contribuir, siga estes passos:
1. **Fork o repositório**
2. Crie uma branch com sua funcionalidade:
   ```sh
   git checkout -b minha-feature
   ```
3. Commit suas mudanças:
   ```sh
   git commit -m 'Adicionando nova funcionalidade'
   ```
4. Suba a branch:
   ```sh
   git push origin minha-feature
   ```
5. **Abra um Pull Request** 🚀

---

## 📜 Licença
Este projeto está sob a licença MIT. Sinta-se livre para utilizar e modificar. 😊

