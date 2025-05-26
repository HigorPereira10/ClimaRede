package com.climarede;

import com.climarede.login.view.LoginWindow;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Inicia a aplicação pela tela de login
        SwingUtilities.invokeLater(() -> new LoginWindow().setVisible(true));
    }
}