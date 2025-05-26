package com.climarede.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import org.json.JSONObject;

//Classe responsável por realizar a consulta de clima usando a API WeatherAPI
public class ClimaController {

	// Chave de API usada para autenticar as requisições
    private static final String API_KEY = "eb0121b5cc09474d87b173657241409";

    // Método que realiza a consulta do clima e retorna as informações como array de strings
    public static String[] consultarClimaComoArray(String cidade) {
        try {
        	// Codifica o nome da cidade para ser usado na URL
            String cidadeCodificada = URLEncoder.encode(cidade, StandardCharsets.UTF_8);
            // Monta a URL da requisição
            String urlStr = "http://api.weatherapi.com/v1/forecast.json?key=" + API_KEY + "&q=" + cidadeCodificada + "&lang=pt&days=1";

            // Cria conexão HTTP
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET"); // Método GET
            
            // Lê a resposta da API
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder resposta = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                resposta.append(inputLine); // Concatena cada linha da resposta
            }
            in.close();

            // Converte a resposta em JSON
            JSONObject json = new JSONObject(resposta.toString());
            
            // Extrai os dados principais do JSON
            String nomeCidade = json.getJSONObject("location").getString("name");
            String condicao = json.getJSONObject("current").getJSONObject("condition").getString("text");
            double temperatura = json.getJSONObject("current").getDouble("temp_c");
            double max = json.getJSONObject("forecast").getJSONArray("forecastday").getJSONObject(0)
                    .getJSONObject("day").getDouble("maxtemp_c");
            double min = json.getJSONObject("forecast").getJSONArray("forecastday").getJSONObject(0)
                    .getJSONObject("day").getDouble("mintemp_c");

            // Define um ícone baseado na condição climática
            String icone = escolherIcone(condicao.toLowerCase());

            // Retorna os dados como array de strings
            return new String[] {
                    nomeCidade,
                    temperatura + "°C",
                    condicao,
                    max + "°C",
                    min + "°C",
                    icone 
            };
        } catch (Exception e) {
            return null;  // Em caso de erro, retorna null
        }
    }

    // Método auxiliar para escolher o ícone conforme a condição do tempo
    private static String escolherIcone(String condicao) {
        if (condicao.contains("trovoada") || condicao.contains("raio")) {
            return "trovoada.png";
        } else if (condicao.contains("nublado") || condicao.contains("encoberto")) {
            return "dia-nublado.png";
        } else if (condicao.contains("sol") || condicao.contains("ensolarado")) {
            return "ensolarado.png";
        } else {
            return "dia-nublado.png"; // padrão
        }
    }
}
