package com.climarede.network;

import java.io.*;
import java.net.*;

// Classe responsável por gerenciar conexão com o servidor de chat
public class Cliente {

    private Socket socket;
    private PrintWriter saida;
    private BufferedReader entrada;

    // Interface para notificar nova mensagem
    public interface MensagemListener {
        void novaMensagem(String mensagem);
    }

    // Conecta ao servidor
    public void conectar(String host, int porta, MensagemListener listener) throws IOException {
        socket = new Socket(host, porta); // Cria o socket para conexão com o servidor
        entrada = new BufferedReader(new InputStreamReader(socket.getInputStream())); // Fluxo de entrada
        saida = new PrintWriter(socket.getOutputStream(), true); // Fluxo de saída com autoflush ativado

        // Thread para ouvir novas mensagens
        new Thread(() -> {
            String msg;
            try {
                while ((msg = entrada.readLine()) != null) {
                    listener.novaMensagem(msg);
                }
            } catch (IOException e) {
                System.out.println("Conexão encerrada.");
            }
        }).start();
    }

    // Envia uma mensagem ao servidor
    public void enviarMensagem(String mensagem) {
        if (saida != null) {
            saida.println(mensagem);
        }
    }
    
 // Envia o nome do usuário como primeira mensagem
    public void enviarNome(String nome) {
        saida.println(nome);
    }
    
 // Retorna o canal de leitura para receber mensagens
    public BufferedReader getInput() {
        return entrada;
    }

    // Encerra conexão
    public void desconectar() throws IOException {
        if (socket != null) {
            socket.close();
        }
    }
}
