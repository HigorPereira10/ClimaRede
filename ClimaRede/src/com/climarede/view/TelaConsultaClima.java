package com.climarede.view;

import com.climarede.controller.ClimaController;
import javax.swing.*;
import java.awt.*;

public class TelaConsultaClima extends JFrame {

    // Campos e rótulos usados na interface
    private JTextField campoCidade;
    private JLabel labelCidade;
    private JLabel labelTemperatura;
    private JLabel labelCondicao;
    private JLabel labelMax;
    private JLabel labelMin;
    private JLabel iconeClima;

    public TelaConsultaClima() {
        setTitle("Consulta Climática");           // Título da janela
        setSize(350, 400);                         // Tamanho da janela
        setLocationRelativeTo(null);               // Centraliza na tela
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Fecha apenas essa janela

        // Cria um painel com fundo em gradiente
        JPanel painelGradiente = new JPanel() {
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                Color cor1 = new Color(0, 70, 50);                   // Cor inicial do gradiente
                Color cor2 = new Color(46, 204, 113);                // Cor final do gradiente
                GradientPaint gp = new GradientPaint(0, 0, cor1, 0, getHeight(), cor2);
                g2d.setPaint(gp);                                   // Aplica o gradiente
                g2d.fillRect(0, 0, getWidth(), getHeight());        // Pinta o fundo
            }
        };
        painelGradiente.setLayout(null); // Layout absoluto para posicionamento livre

        // Título da interface
        JLabel titulo = new JLabel("Consulta Climática");
        titulo.setBounds(90, 20, 200, 30);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setForeground(Color.WHITE);
        painelGradiente.add(titulo);

        // Rótulo "Cidade"
        JLabel label = new JLabel("Cidade");
        label.setBounds(40, 60, 100, 20);
        label.setForeground(Color.WHITE);
        painelGradiente.add(label);

        // Campo para digitar a cidade
        campoCidade = new JTextField();
        campoCidade.setBounds(40, 80, 260, 30);
        painelGradiente.add(campoCidade);

        // Botão para consultar o clima
        JButton btnConsultar = new JButton("Consultar");
        btnConsultar.setBounds(100, 120, 120, 30);
        btnConsultar.setBackground(new Color(46, 204, 113));
        btnConsultar.setForeground(Color.WHITE);
        btnConsultar.setFocusPainted(false); // Remove a borda de foco
        painelGradiente.add(btnConsultar);

        // Rótulo com o nome da cidade consultada
        labelCidade = new JLabel(" ");
        labelCidade.setBounds(40, 160, 250, 30);
        labelCidade.setFont(new Font("Arial", Font.BOLD, 20));
        labelCidade.setForeground(Color.WHITE);
        painelGradiente.add(labelCidade);

        // Ícone representando o clima (ex: sol, nuvens)
        iconeClima = new JLabel();
        iconeClima.setBounds(40, 200, 64, 64);
        painelGradiente.add(iconeClima);

        // Temperatura atual
        labelTemperatura = new JLabel(" ");
        labelTemperatura.setBounds(110, 200, 150, 40);
        labelTemperatura.setFont(new Font("Arial", Font.BOLD, 32));
        labelTemperatura.setForeground(Color.WHITE);
        painelGradiente.add(labelTemperatura);

        // Condição do tempo (ex: Ensolarado, Chuvoso)
        labelCondicao = new JLabel(" ");
        labelCondicao.setBounds(40, 260, 250, 25);
        labelCondicao.setForeground(Color.WHITE);
        labelCondicao.setFont(new Font("Arial", Font.PLAIN, 16));
        painelGradiente.add(labelCondicao);

        // Temperatura máxima
        labelMax = new JLabel(" ");
        labelMax.setBounds(40, 290, 120, 20);
        labelMax.setForeground(Color.WHITE);
        painelGradiente.add(labelMax);

        // Temperatura mínima
        labelMin = new JLabel(" ");
        labelMin.setBounds(170, 290, 120, 20);
        labelMin.setForeground(Color.WHITE);
        painelGradiente.add(labelMin);

        // Ação do botão para buscar os dados do clima
        btnConsultar.addActionListener(e -> consultarClima());

        add(painelGradiente); // Adiciona o painel à janela
        setVisible(true);     // Torna a janela visível
    }

    // Método responsável por buscar e exibir os dados do clima
    private void consultarClima() {
        String cidade = campoCidade.getText().trim(); // Obtém o texto digitado
        if (!cidade.isEmpty()) {
            // Consulta os dados climáticos através do controller
            String[] dados = ClimaController.consultarClimaComoArray(cidade);
            if (dados != null) {
                // Preenche os rótulos com os dados recebidos
                labelCidade.setText(dados[0]);
                labelTemperatura.setText(dados[1]);
                labelCondicao.setText(dados[2]);
                labelMax.setText("Máx: " + dados[3]);
                labelMin.setText("Min: " + dados[4]);

                // Carrega o ícone correspondente ao clima
                ImageIcon icone = new ImageIcon("src/imagens/" + dados[5]);
                Image imagemRedimensionada = icone.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                iconeClima.setIcon(new ImageIcon(imagemRedimensionada));
            } else {
                // Caso ocorra erro na consulta
                JOptionPane.showMessageDialog(this, "Erro ao consultar o clima.");
            }
        }
    }
}
