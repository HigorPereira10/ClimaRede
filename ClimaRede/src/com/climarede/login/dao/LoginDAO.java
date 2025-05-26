package com.climarede.login.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {

    // Verifica se o login e senha correspondem no banco
    public boolean verificarCredenciais(String login, String senha) {
        String sql = "SELECT senha FROM usuarios WHERE login = ?";

        try (Connection conn = MySqlConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, login);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String senhaHash = rs.getString("senha");
                    return com.climarede.login.utils.SenhaUtils.verificarSenha(senha, senhaHash);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Cadastra o usuário com nome, sobrenome, login e senha já criptografada
    public boolean cadastrarUsuarioCompleto(String nome, String sobrenome, String login, String senhaHash) {
        String sql = "INSERT INTO usuarios (nome, sobrenome, login, senha) VALUES (?, ?, ?, ?)";

        try (Connection conn = MySqlConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setString(2, sobrenome);
            stmt.setString(3, login);
            stmt.setString(4, senhaHash);

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar usuário:");
            e.printStackTrace(); // ← importante para entender o que falhou
            return false;
        }
    }

    // Cadastra um usuário simples (caso use em outro ponto)
    public boolean cadastrarUsuario(String login, String senhaHash) {
        String sql = "INSERT INTO usuarios (login, senha) VALUES (?, ?)";

        try (Connection conn = MySqlConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, login);
            stmt.setString(2, senhaHash);

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
