<p align="center">
  <img src="ClimaRede/src/Imagens/logo.png" alt="Logo ClimaRede" width="180"/>
</p>

# 🌱 ClimaRede

ClimaRede é uma aplicação Java desenvolvida como parte de um trabalho semestral do curso de **Ciência da Computação**. O sistema combina funcionalidades de **consulta climática em tempo real** com um **chat integrado**, promovendo a interatividade entre os usuários e a conscientização ambiental.

## 💡 Funcionalidades

- 🔒 Tela de login com cadastro de novos usuários (dados armazenados em banco de dados MySQL)
- ☀️ Consulta do clima atual de qualquer cidade utilizando a API WeatherAPI
- 💬 Chat TCP/IP em rede, com troca de mensagens em tempo real
- 🌿 Interface moderna com temática voltada à preservação ambiental
- 📦 Estrutura modular seguindo boas práticas de desenvolvimento

## 🛠️ Tecnologias utilizadas

- Java 17
- Swing (Interface Gráfica)
- MySQL
- JDBC
- WeatherAPI (https://www.weatherapi.com/)
- Maven

## 🧩 Estrutura do Projeto

ClimaRede/  
  ├── src/  
  │ ├── com.climarede/   
  │ │ ├── Main.java  
  │ │ ├── controller/  
  │ │ ├── login/  
  │ │ ├── network/  
  │ │ └── view/  
  │ │ └── imagens/ 
  ├── .gitignore  
  └── pom.xml  


## 🖼️ Interface do sistema

As principais telas do sistema incluem:

- Tela de login
- Cadastro de novo usuário
- Menu principal
- Consulta climática
- Chat em tempo real

## 🚀 Como executar o projeto

1. Certifique-se de ter o **MySQL Server** rodando e configure a conexão no arquivo `MySqlConnection.java`
2. Clone este repositório:
   ```bash
   git clone https://github.com/HigorPereira10/ClimaRede.git
3. Compile o projeto com Maven:
   ```bash
   mvn clean install

4. Execute a aplicação
   ```bash
   java -cp target/ClimaRede-0.0.1-SNAPSHOT.jar com.climarede.Main

## ⚠️ Observações

- Para utilizar o chat em máquinas diferentes, é necessário que o servidor esteja rodando em uma delas e o IP seja informado corretamente.
- A API WeatherAPI requer uma chave gratuita que pode ser obtida no site oficial.

## 👨‍💻 Autoria

Desenvolvido por estudantes de Ciência da Computação como projeto semestral.
Orientado por professores da Universidade Paulista – UNIP.

