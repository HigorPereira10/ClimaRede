package com.climarede.login.view;

import javax.swing.*;
import java.awt.*;
import com.climarede.login.controller.LoginController;


 // Janela de cadastro de novo usuário.
 // Interface gráfica construída com Swing.

public class CadastroWindow extends JFrame {

    // Campos de entrada do formulário
    private JTextField campoNome, campoSobrenome, campoLogin;
    private JPasswordField campoSenha;
    private JLabel status; // Rótulo para exibir mensagens de status
    private LoginController loginController; // Controlador responsável pelo cadastro

    // Inicializa a interface e configura a janela.
    public CadastroWindow() {
        loginController = new LoginController(); // Inicializa o controlador

        // Configurações da janela
        setTitle("Cadastro de Novo Usuário");
        setSize(400, 320);
        setLocationRelativeTo(null); // Centraliza a janela
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false); // Impede redimensionamento da janela

        criarInterface(); // Cria e adiciona os componentes visuais
    }

    // Cria a interface gráfica da janela de cadastro. 
    // Inclui painel com fundo em gradiente, campos de texto e botão.
    
    private void criarInterface() {
        // Painel personalizado com gradiente de cor
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
        painelGradiente.setLayout(null); // Layout absoluto
        setContentPane(painelGradiente); // Define o painel como conteúdo principal

        // Criação dos rótulos
        JLabel lblNome = new JLabel("Nome:");
        JLabel lblSobrenome = new JLabel("Sobrenome:");
        JLabel lblLogin = new JLabel("Login:");
        JLabel lblSenha = new JLabel("Senha:");
        status = new JLabel(" "); // Inicializa com espaço em branco

        // Criação dos campos de entrada
        campoNome = new JTextField();
        campoSobrenome = new JTextField();
        campoLogin = new JTextField();
        campoSenha = new JPasswordField();
        JButton btnCadastrar = new JButton("Cadastrar");

        // Definição das posições dos componentes (layout absoluto)
        lblNome.setBounds(40, 20, 100, 25);
        campoNome.setBounds(150, 20, 200, 25);

        lblSobrenome.setBounds(40, 60, 100, 25);
        campoSobrenome.setBounds(150, 60, 200, 25);

        lblLogin.setBounds(40, 100, 100, 25);
        campoLogin.setBounds(150, 100, 200, 25);

        lblSenha.setBounds(40, 140, 100, 25);
        campoSenha.setBounds(150, 140, 200, 25);

        btnCadastrar.setBounds(130, 190, 120, 30);
        status.setBounds(40, 230, 320, 30);

        // Personalização visual (cores e fontes)
        Color branco = Color.WHITE;
        Font fonteLabel = new Font("Arial", Font.PLAIN, 14);

        lblNome.setForeground(branco);
        lblSobrenome.setForeground(branco);
        lblLogin.setForeground(branco);
        lblSenha.setForeground(branco);
        status.setForeground(Color.YELLOW);

        lblNome.setFont(fonteLabel);
        lblSobrenome.setFont(fonteLabel);
        lblLogin.setFont(fonteLabel);
        lblSenha.setFont(fonteLabel);

        btnCadastrar.setBackground(new Color(0x4CAF50));
        btnCadastrar.setForeground(Color.WHITE);

        // Adição dos componentes ao painel
        painelGradiente.add(lblNome);        painelGradiente.add(campoNome);
        painelGradiente.add(lblSobrenome);   painelGradiente.add(campoSobrenome);
        painelGradiente.add(lblLogin);       painelGradiente.add(campoLogin);
        painelGradiente.add(lblSenha);       painelGradiente.add(campoSenha);
        painelGradiente.add(btnCadastrar);   painelGradiente.add(status);

        // Ação do botão de cadastro
        btnCadastrar.addActionListener(e -> cadastrar());
    }

    // Método responsável por realizar o cadastro do usuário a partir dos dados preenchidos no formulário.
     
    private void cadastrar() {
        // Coleta e limpeza dos dados inseridos
        String nome = campoNome.getText().trim();
        String sobrenome = campoSobrenome.getText().trim();
        String login = campoLogin.getText().trim();
        String senha = String.valueOf(campoSenha.getPassword());

        // Chama o controlador para cadastrar o usuário
        boolean sucesso = loginController.cadastrarUsuarioCompleto(nome, sobrenome, login, senha);

        // Atualiza a mensagem de status de acordo com o resultado
        if (sucesso) {
            status.setText("Cadastro realizado com sucesso!");
        } else {
            status.setText("Erro ao cadastrar. Verifique os dados.");
        }
    }
}
