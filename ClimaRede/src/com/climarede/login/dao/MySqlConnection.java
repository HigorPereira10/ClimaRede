package com.climarede.login.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Classe responsável por fornecer conexões com o banco de dados MySQL
public class MySqlConnection {

    // Configurações da conexão (ajuste para o seu banco)
    private static final String URL = "jdbc:mysql://localhost:3306/climarede?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    private static String status = "Não conectado...";

    // Retorna uma nova conexão com o banco
    public static Connection getConnection() {
        try {
            String driverName = "com.mysql.cj.jdbc.Driver";
            Class.forName(driverName); // Carrega o driver MySQL
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

            if (connection != null) {
                status = "STATUS ---> CONECTADO COM SUCESSO";
                System.out.println(status);
            } else {
                status = "STATUS ---> FALHA NA CONEXÃO";
                System.out.println(status);
            }

            return connection;

        } catch (ClassNotFoundException e) {
            System.out.println("O DRIVER DO MYSQL NÃO FOI ENCONTRADO.");
            return null;
        } catch (SQLException e) {
            System.out.println("NÃO FOI POSSÍVEL CONECTAR AO BANCO DE DADOS.");
            e.printStackTrace();
            return null;
        }
    }

    // Retorna o status atual da conexão
    public static String statusConnection() {
        return status;
    }

    // Encerra uma conexão fornecida
    public static boolean closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
                return true;
            }
            return false;
        } catch (SQLException e) {
            return false;
        }
    }

    // Reinicia a conexão
    public static Connection restartConnection() {
        Connection connection = getConnection();
        return connection;
    }
}
