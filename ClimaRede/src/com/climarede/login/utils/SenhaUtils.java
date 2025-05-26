package com.climarede.login.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SenhaUtils {

    // Gera o hash da senha (usando SHA-256)
    public static String gerarHash(String senha) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(senha.getBytes());

            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erro ao gerar hash da senha", e);
        }
    }

    // Verifica se a senha fornecida (sem hash) corresponde ao hash salvo no banco
    public static boolean verificarSenha(String senhaDigitada, String senhaHashDoBanco) {
        String senhaDigitadaHash = gerarHash(senhaDigitada);
        return senhaDigitadaHash.equals(senhaHashDoBanco);
    }
}
