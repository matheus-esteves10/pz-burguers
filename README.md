# 🍔 PZBurguer - Sistema de Pedidos com Microsserviços

Projeto desenvolvido para uma hamburgueria fictícia, que permite aos clientes realizarem pedidos online, enquanto o restaurante pode gerenciá-los de forma eficiente.

## 🧩 Arquitetura

O sistema é dividido em **3 microsserviços** principais:

### 1. `pz-burguer` (Serviço Principal)
- Responsável por:
  - Cadastro e autenticação de usuários (`Spring Security + JWT`)
  - Criação e listagem de pedidos
  - Consulta e gestão de pedidos pelo restaurante
  - Cadastro de itens disponíveis no cardápio
- Tecnologias:
  - Spring Boot
  - Spring Security
  - JPA/Hibernate
  - H2 SQL 
  - RabbitMQ (mensageria)
  - librepdf (gerar nota fiscal)
  
---

### 2. `email-service`
- Responsável por envio de e-mails transacionais usando `JavaMailSender`
- Funcionalidades:
  - Envio de e-mail de boas-vindas no cadastro
  - Envio de comprovante (PDF) do pedido via anexo
- Tecnologias:
  - Spring Boot
  - RabbitMQ
  - JavaMailSender (SMTP)

---

### 3. `pagamento-service`
- Simula o processamento de pagamentos.
- Ao final, envia uma mensagem com o status do pagamento ao serviço principal (APROVADO caso feito ou ERRO caso o pagamento não seja concluído).
- Tecnologias:
  - Spring Boot
  - RabbitMQ

---

## 🔄 Comunicação entre Microsserviços

Toda a comunicação entre os microsserviços é feita de forma **assíncrona** via **RabbitMQ**.

### 🔄Exemplo de fluxo:

 1️⃣ Cliente realiza o cadastro → pz-burguer → email-service envia e-mail de boas vindas
 2️⃣ Cliente realiza um pedido → pz-burguer salva pedido
 3️⃣ pz-burguer envia mensagem para pagamento-service
 4️⃣ pagamento-service processa o pagamento e responde com status
 5️⃣ Se aprovado, pz-burguer:
 • gera comprovante em PDF
 • envia mensagem para email-service
 • email-service envia e-mail com anexo da nota fiscal em PDF

---

## 🔐 Segurança

- Autenticação via JWT (Spring Security)
- Perfis de usuário:
  - `CLIENTE`: pode se cadastrar, fazer pedidos e cancelar pedidos
  - `RESTAURANTE`: pode gerenciar ou listar pedidos e cadastrar itens

---

## 📂 Estrutura do Projeto

```
├── pz-burguer-service       # Serviço principal (pedidos, usuários, itens)
├── pagamento-service        # Simulação de pagamentos
├── email-service            # Envio de e-mails
