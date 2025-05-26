package com.climarede.login.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.climarede.login.controller.LoginController;

public class LoginWindow extends JFrame {

    // Componentes da interface gráfica
    private JTextField jtf_login;
    private JPasswordField jpf_senha;
    private JButton jb_login, jb_cadastrar;
    private JCheckBox cb_mostrarSenha, cb_novoUsuario;
    private JLabel lb_status;
    private LoginController loginController;

    // Construtor da janela de login
    public LoginWindow() {
        this.loginController = new LoginController();     // Instancia o controlador de login
        this.setTitle("Sistema de Login");                // Define o título da janela
        this.setSize(400, 300);                           // Define o tamanho da janela
        this.setLocationRelativeTo(null);                 // Centraliza a janela na tela
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);     // Fecha apenas esta janela ao clicar no "X"
        this.setResizable(false);                         // Impede redimensionamento
        criarInterface();                                 // Inicializa os componentes visuais
    }

    // Método responsável por criar e configurar a interface
    private void criarInterface() {
        // Painel com fundo em gradiente (do verde escuro ao verde claro)
        JPanel painelGradiente = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                Color startColor = new Color(0x143c2b);
                Color endColor = new Color(0x3cb371);
                GradientPaint gradient = new GradientPaint(0, 0, startColor, 0, getHeight(), endColor);
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        // Layout vertical com margens internas
        painelGradiente.setLayout(new BoxLayout(painelGradiente, BoxLayout.Y_AXIS));
        painelGradiente.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        setContentPane(painelGradiente); // Define o painel como conteúdo da janela

        // Inicialização dos componentes
        jtf_login = new JTextField(15);
        jpf_senha = new JPasswordField(15);
        cb_mostrarSenha = new JCheckBox("Mostrar senha");
        cb_novoUsuario = new JCheckBox("Novo usuário?");
        jb_login = new JButton("Entrar");
        jb_cadastrar = new JButton("Cadastrar");
        jb_cadastrar.setVisible(false); // Oculto até o checkbox ser marcado
        lb_status = new JLabel(" ");
        lb_status.setForeground(Color.RED);
        lb_status.setHorizontalAlignment(SwingConstants.CENTER);

        // Rótulos dos campos
        JLabel lblLogin = new JLabel("Login:");
        JLabel lblSenha = new JLabel("Senha:");

        // Estilo dos textos e componentes
        lblLogin.setForeground(Color.WHITE);
        lblSenha.setForeground(Color.WHITE);
        cb_mostrarSenha.setForeground(Color.WHITE);
        cb_mostrarSenha.setOpaque(false);
        cb_mostrarSenha.setBackground(null);

        cb_novoUsuario.setForeground(Color.WHITE);
        cb_novoUsuario.setOpaque(false);
        cb_novoUsuario.setBackground(null);

        jb_login.setBackground(new Color(0x4CAF50));     // Cor de fundo dos botões
        jb_login.setForeground(Color.WHITE);
        jb_cadastrar.setBackground(new Color(0x4CAF50));
        jb_cadastrar.setForeground(Color.WHITE);

        // Adiciona os componentes ao painel principal
        painelGradiente.add(lblLogin);
        painelGradiente.add(jtf_login);
        painelGradiente.add(Box.createVerticalStrut(10)); // Espaçamento vertical

        painelGradiente.add(lblSenha);
        painelGradiente.add(jpf_senha);
        painelGradiente.add(cb_mostrarSenha);
        painelGradiente.add(Box.createVerticalStrut(15));

        // Painel inferior com botão de login e checkbox de novo usuário
        JPanel painelInferior = new JPanel(new BorderLayout());
        painelInferior.setOpaque(false);
        painelInferior.add(jb_login, BorderLayout.WEST);
        painelInferior.add(cb_novoUsuario, BorderLayout.EAST);

        painelGradiente.add(painelInferior);
        painelGradiente.add(Box.createVerticalStrut(10));
        painelGradiente.add(jb_cadastrar);
        painelGradiente.add(Box.createVerticalStrut(10));
        painelGradiente.add(lb_status);

        // Inicializa os eventos dos botões e checkboxes
        eventos();
    }

    // Define os comportamentos dos botões e checkboxes
    private void eventos() {

        // Alterna visibilidade da senha
        cb_mostrarSenha.addActionListener(e ->
            jpf_senha.setEchoChar(cb_mostrarSenha.isSelected() ? (char) 0 : '*')
        );

        // Mostra ou oculta botão de cadastro ao marcar "Novo usuário?"
        cb_novoUsuario.addActionListener(e ->
            jb_cadastrar.setVisible(cb_novoUsuario.isSelected())
        );

        // Ação do botão de login
        jb_login.addActionListener(e -> {
            String login = jtf_login.getText();
            String senha = String.valueOf(jpf_senha.getPassword());

            // Valida as credenciais via controlador
            boolean validou = loginController.validarLogin(login, senha);
            if (validou) {
                lb_status.setForeground(new Color(0, 128, 0)); // Verde
                lb_status.setText("Login bem-sucedido!");
                dispose(); // Fecha a janela atual
                new com.climarede.view.TelaMenuPrincipal(); // Abre a tela principal do sistema
            } else {
                lb_status.setForeground(Color.RED);
                lb_status.setText("Usuário ou senha incorretos.");
            }
        });

        // Ação do botão de cadastro (abre a tela CadastroWindow)
        jb_cadastrar.addActionListener(e ->
            new CadastroWindow().setVisible(true)
        );
    }
}
