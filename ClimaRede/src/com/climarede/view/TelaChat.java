package com.climarede.view;

import com.climarede.network.Cliente;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class TelaChat extends JFrame {

    private Cliente cliente;                  // Cliente responsável pela conexão com o servidor
    private JTextArea areaMensagens;          // Área onde as mensagens serão exibidas
    private JTextField campoMensagem;         // Campo onde o usuário digita a mensagem
    private String nomeUsuario;               // Nome do usuário conectado ao chat

    // Construtor da janela de chat
    public TelaChat() {
        setTitle("Chat ClimaRede");                  // Define o título da janela
        setSize(400, 500);                           // Define o tamanho da janela
        setLocationRelativeTo(null);                 // Centraliza a janela na tela
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Fecha somente esta janela

        // Painel com fundo em gradiente
        JPanel painelGradiente = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);             // Garante a renderização dos componentes filhos
                Graphics2D g2d = (Graphics2D) g;
                Color cor1 = new Color(0, 70, 50);    // Cor inicial do gradiente
                Color cor2 = new Color(46, 204, 113); // Cor final do gradiente
                GradientPaint gp = new GradientPaint(0, 0, cor1, 0, getHeight(), cor2);
                g2d.setPaint(gp);                    // Aplica o gradiente
                g2d.fillRect(0, 0, getWidth(), getHeight()); // Preenche o painel
            }
        };
        painelGradiente.setLayout(null); // Usa posicionamento absoluto

        // Título do chat
        JLabel titulo = new JLabel("Chat ClimaRede");
        titulo.setBounds(120, 20, 200, 30);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setForeground(Color.WHITE);
        painelGradiente.add(titulo);

        // Área de exibição de mensagens
        areaMensagens = new JTextArea();
        areaMensagens.setEditable(false);                          // Impede edição
        areaMensagens.setBackground(new Color(0x2E8B57));          // Cor de fundo
        areaMensagens.setForeground(Color.WHITE);                  // Cor do texto
        areaMensagens.setFont(new Font("Arial", Font.PLAIN, 14));  // Fonte do texto
        areaMensagens.setLineWrap(true);                           // Quebra de linha automática
        areaMensagens.setWrapStyleWord(true);                      // Quebra por palavra

        // Scroll para a área de mensagens
        JScrollPane scroll = new JScrollPane(areaMensagens);
        scroll.setBounds(30, 60, 320, 280);
        scroll.setBorder(BorderFactory.createEmptyBorder()); // Remove bordas visuais
        scroll.setOpaque(false);                             // Deixa transparente
        scroll.getViewport().setOpaque(false);               // Deixa o conteúdo transparente
        painelGradiente.add(scroll);

        // Campo para digitar mensagens
        campoMensagem = new JTextField();
        campoMensagem.setBounds(30, 360, 320, 35);
        campoMensagem.setFont(new Font("Arial", Font.PLAIN, 14));
        campoMensagem.setBackground(Color.WHITE);
        campoMensagem.setForeground(Color.BLACK);
        campoMensagem.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        painelGradiente.add(campoMensagem);

        // Botão para enviar mensagens
        JButton btnEnviar = new JButton("Enviar");
        btnEnviar.setBounds(125, 410, 120, 35);
        btnEnviar.setBackground(new Color(46, 204, 113));
        btnEnviar.setForeground(Color.WHITE);
        btnEnviar.setFont(new Font("Arial", Font.BOLD, 16));
        btnEnviar.setFocusPainted(false);
        painelGradiente.add(btnEnviar);

        // Evento para enviar mensagem ao clicar no botão
        btnEnviar.addActionListener(e -> enviarMensagem());

        // Evento para enviar mensagem ao pressionar Enter no campo
        campoMensagem.addActionListener(e -> enviarMensagem());

        // Solicita IP do servidor ao usuário
        String ipServidor = JOptionPane.showInputDialog(this, "Digite o IP do servidor:", "localhost");

        // Solicita nome do usuário
        nomeUsuario = JOptionPane.showInputDialog(this, "Digite seu nome:");

        // Validação do nome e IP
        if (ipServidor == null || nomeUsuario == null || nomeUsuario.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nome ou IP inválido.");
            dispose(); // Fecha a janela se os dados forem inválidos
            return;
        }

        try {
            cliente = new Cliente(); // Instancia o cliente
            cliente.conectar(ipServidor, 12345, mensagem -> {
                // Ao receber mensagem, adiciona na área de texto
                SwingUtilities.invokeLater(() -> areaMensagens.append(mensagem + "\n"));
            });

            cliente.enviarNome(nomeUsuario); // Envia nome para o servidor
        } catch (IOException e) {
            // Exibe mensagem de erro caso não conecte
            JOptionPane.showMessageDialog(this, "Erro ao conectar ao servidor: " + e.getMessage());
            dispose(); // Fecha a janela
        }

        // Adiciona o painel na janela
        add(painelGradiente);
        setVisible(true); // Torna a janela visível
    }

    // Método responsável por enviar a mensagem para o servidor
    private void enviarMensagem() {
        String msg = campoMensagem.getText().trim(); // Captura texto e remove espaços
        if (!msg.isEmpty()) {
            cliente.enviarMensagem("[" + nomeUsuario + "]: " + msg); // Envia com nome formatado
            campoMensagem.setText(""); // Limpa o campo de mensagem
        }
    }
}
