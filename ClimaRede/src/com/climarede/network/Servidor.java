package com.climarede.network;

import java.io.*;
import java.net.*;
import java.util.*;

// Classe responsável por iniciar o servidor de chat
public class Servidor {

    // Lista com todos os clientes conectados
    private static final List<PrintWriter> clientes = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        // Porta em que o servidor ficará escutando
        ServerSocket servidor = new ServerSocket(12345);
        System.out.println("Servidor de chat iniciado na porta 12345...");

        // Loop principal para aceitar novos clientes
        while (true) {
            Socket clienteSocket = servidor.accept(); // Aceita conexão
            System.out.println("Novo cliente conectado: " + clienteSocket.getInetAddress());

            // Cria uma thread para lidar com esse cliente
            new Thread(() -> {
                try {
                    // Cria canais de entrada e saída
                    BufferedReader entrada = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
                    PrintWriter saida = new PrintWriter(clienteSocket.getOutputStream(), true);

                    // Adiciona esse cliente na lista
                    synchronized (clientes) {
                        clientes.add(saida);
                    }

                    // Lê mensagens do cliente
                    String mensagem;
                    while ((mensagem = entrada.readLine()) != null) {
                        // Exibe no servidor
                        System.out.println("Mensagem recebida: " + mensagem);

                        // Envia para todos os clientes conectados
                        synchronized (clientes) {
                            for (PrintWriter out : clientes) {
                                out.println(mensagem);
                            }
                        }
                    }

                    // Remove cliente ao desconectar
                    synchronized (clientes) {
                        clientes.remove(saida);
                    }

                    clienteSocket.close();

                } catch (IOException e) {
                    System.out.println("Erro com cliente: " + e.getMessage());
                }
            }).start(); // Inicia a thread
        }
    }
}
