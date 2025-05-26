package com.climarede.login.utils;

public class Validador {

    public static boolean validarLogin(String login) {
        return login != null && login.length() >= 4 && login.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    }

    public static boolean validarSenha(String senha) {
        return senha != null && senha.length() >= 6;
    }

    public static boolean validarNome(String nome) {
        return nome != null && nome.length() >= 2 && nome.matches("[A-Za-zÀ-ÿ\\s]+");
    }

    public static boolean validarSobrenome(String sobrenome) {
        return sobrenome != null && sobrenome.length() >= 2 && sobrenome.matches("[A-Za-zÀ-ÿ\\s]+");
    }
}
