package com.climarede.view;

import javax.swing.*;
import java.awt.*;

// Tela principal que oferece acesso às funcionalidades de Clima e Chat
public class TelaMenuPrincipal extends JFrame {

    // Construtor da tela principal
    public TelaMenuPrincipal() {
        setTitle("ClimaRede - Menu Principal"); // Define o título da janela
        setSize(400, 450); // Define o tamanho da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Finaliza o programa ao fechar a janela
        setLocationRelativeTo(null); // Centraliza a janela na tela
        setResizable(false); // Impede o redimensionamento da janela

        configurarInterface(); // Chama o método que monta a interface gráfica
        setVisible(true); // Exibe a janela
    }

    // Método que configura todos os elementos da interface gráfica
    private void configurarInterface() {
        // Criação de painel com fundo em gradiente
        JPanel painel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g); // Garante que o painel seja desenhado corretamente
                Graphics2D g2d = (Graphics2D) g;

                // Define as cores do gradiente
                Color cor1 = new Color(23, 50, 48);
                Color cor2 = new Color(46, 139, 87);
                GradientPaint gradiente = new GradientPaint(0, 0, cor1, 0, getHeight(), cor2);

                // Aplica o gradiente no fundo do painel
                g2d.setPaint(gradiente);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        // Define o layout em coluna e espaçamento interno do painel
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // Criação do componente para o logo
        JLabel logo = new JLabel();
        logo.setAlignmentX(Component.CENTER_ALIGNMENT); // Centraliza horizontalmente

        // Carrega o ícone do logo e redimensiona
        ImageIcon icone = new ImageIcon("src/Imagens/logo.png"); // Caminho da imagem
        Image imagemRedimensionada = icone.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        logo.setIcon(new ImageIcon(imagemRedimensionada)); // Define o ícone no JLabel
        painel.add(logo); // Adiciona o logo ao painel
        painel.add(Box.createVerticalStrut(15)); // Espaçamento vertical

        // Título principal do menu
        JLabel titulo = new JLabel("Bem-vindo ao ClimaRede");
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setFont(new Font("Arial", Font.BOLD, 20)); // Define fonte
        titulo.setForeground(Color.WHITE); // Cor do texto
        painel.add(titulo);
        painel.add(Box.createVerticalStrut(40)); // Espaço após o título

        // Botão para consultar o clima
        JButton btnConsulta = criarBotaoVerde("Consultar Clima");
        btnConsulta.addActionListener(e -> new TelaConsultaClima()); // Abre a tela de clima
        painel.add(btnConsulta);
        painel.add(Box.createVerticalStrut(20)); // Espaço entre os botões

        // Botão para acessar o chat
        JButton btnChat = criarBotaoVerde("Acessar Chat");
        btnChat.addActionListener(e -> new TelaChat()); // Abre a tela do chat
        painel.add(btnChat);

        add(painel); // Adiciona o painel principal à janela
    }

    // Método utilitário para criar botões verdes com estilo padronizado
    private JButton criarBotaoVerde(String texto) {
        JButton botao = new JButton(texto); // Cria botão com texto informado
        botao.setMaximumSize(new Dimension(200, 40)); // Define tamanho máximo
        botao.setAlignmentX(Component.CENTER_ALIGNMENT); // Centraliza o botão
        botao.setFont(new Font("Arial", Font.BOLD, 16)); // Define a fonte
        botao.setBackground(new Color(76, 175, 80)); // Cor de fundo
        botao.setForeground(Color.WHITE); // Cor do texto
        botao.setFocusPainted(false); // Remove o contorno ao focar
        botao.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Espaçamento interno
        return botao;
    }
}
