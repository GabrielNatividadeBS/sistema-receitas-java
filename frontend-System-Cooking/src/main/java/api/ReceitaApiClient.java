package api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Receita;

public class ReceitaApiClient {

    private final HttpClient client = HttpClient.newHttpClient();
    private final Gson gson = new Gson();

    private static final String API_URL = "http://localhost:8080/receitas";

    public List<Receita> buscarReceitas() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                // Converte o JSON (que é um texto) em uma Lista de objetos Receita
                return gson.fromJson(response.body(), new TypeToken<List<Receita>>() {
                }.getType());
            } else {
                JOptionPane.showMessageDialog(null, "Não foi possível buscar as receitas. Erro da API: " + response.statusCode());
                return Collections.emptyList();
            }
        } catch (IOException | InterruptedException e) {
            JOptionPane.showMessageDialog(null, "Erro de conexão: Não foi possível conectar ao servidor do back-end.");
            return Collections.emptyList();
        }
    }

    public void adicionarReceita(Receita receita) {
        String json = gson.toJson(receita);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 201) {
                JOptionPane.showMessageDialog(null, "Receita adicionada com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao adicionar receita: " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            JOptionPane.showMessageDialog(null, "Erro de conexão ao adicionar receita.");
        }
    }
    
    // Salvar nova receita (POST)
    public boolean salvarReceita(Receita receita) {
        try {
            String json = gson.toJson(receita);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.statusCode() == 201; // 201 = Created
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar receita: " + e.getMessage());
            return false;
        }
    }

    // Atualizar receita existente (PUT)
    public boolean atualizarReceita(Receita receita) {
        try {
            String json = gson.toJson(receita);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL + "/" + receita.getId()))
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.statusCode() == 200; // 200 = OK
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar receita: " + e.getMessage());
            return false;
        }
    }

}
