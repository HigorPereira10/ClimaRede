package com.climarede.login.controller;

import com.climarede.login.dao.LoginDAO;
import com.climarede.login.utils.Validador;
import com.climarede.login.utils.SenhaUtils;

// Classe responsável por intermediar a lógica de login/cadastro entre a interface e o banco
public class LoginController {

    private LoginDAO loginDAO;

    // Construtor que inicializa o DAO
    public LoginController() {
        this.loginDAO = new LoginDAO();
    }

    // Método chamado pela tela de login para validar as credenciais
    public boolean validarLogin(String login, String senha) {
        return loginDAO.verificarCredenciais(login, senha);
    }

    // Método usado em um cadastro simples (login e senha)
    public boolean cadastrarUsuario(String login, String senha) {
        if (!Validador.validarLogin(login) || !Validador.validarSenha(senha)) {
            System.out.println("Login ou senha inválidos.");
            return false;
        }
        return loginDAO.cadastrarUsuario(login, senha);
    }

    // Método de cadastro completo com nome e sobrenome (além de login e senha)
    public boolean cadastrarUsuarioCompleto(String nome, String sobrenome, String login, String senha) {
        System.out.println("Recebido -> Nome: " + nome + ", Sobrenome: " + sobrenome + ", Login: " + login + ", Senha: " + senha);

        if (!Validador.validarNome(nome)) {
            System.out.println("Nome inválido.");
            return false;
        }

        if (!Validador.validarSobrenome(sobrenome)) {
            System.out.println("Sobrenome inválido.");
            return false;
        }

        if (!Validador.validarLogin(login)) {
            System.out.println("Login inválido.");
            return false;
        }

        if (!Validador.validarSenha(senha)) {
            System.out.println("Senha inválida.");
            return false;
        }

        // Criptografa a senha antes de salvar
        String senhaHash = SenhaUtils.gerarHash(senha);
        return loginDAO.cadastrarUsuarioCompleto(nome, sobrenome, login, senhaHash);
    }
}
