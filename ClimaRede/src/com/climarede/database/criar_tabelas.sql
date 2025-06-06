CREATE DATABASE climarede;
USE climarede;

CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100),
    sobrenome VARCHAR(100),
    login VARCHAR(100) UNIQUE,
    senha VARCHAR(255)
);
