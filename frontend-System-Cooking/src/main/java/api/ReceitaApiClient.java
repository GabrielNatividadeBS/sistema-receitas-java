package api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Receita;
import modelo.Categoria;
import java.util.Map;
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
    
    public List<Categoria> buscarTodasCategorias() {
    HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:8080/categorias")) // Nossa nova rota!
            .build();
    try {
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200) {
            return gson.fromJson(response.body(), new TypeToken<List<Categoria>>() {}.getType());
        }
    } catch (IOException | InterruptedException e) {
        JOptionPane.showMessageDialog(null, "Erro ao buscar categorias da API.");
    }
    return Collections.emptyList(); // Retorna lista vazia em caso de erro
}


    // Salvar nova receita (POST)
public Receita salvarReceita(Receita novaReceita) {
    try {
        String jsonBody = gson.toJson(novaReceita);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/receitas"))
                .header("Content-Type", "application/json")
                .POST(BodyPublishers.ofString(jsonBody))
                .build();
                
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        if (response.statusCode() == 201) {
            // Converte a resposta JSON de volta para um objeto Receita
            return gson.fromJson(response.body(), Receita.class);
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao salvar receita. Código: " + response.statusCode());
            return null;
        }
    } catch (IOException | InterruptedException e) {
        JOptionPane.showMessageDialog(null, "Erro de conexão ao salvar a receita.");
        return null;
    }
}

    // Atualizar receita existente (PUT)
    public boolean atualizarReceita(int id, Receita receitaAtualizada) {
        try {
            String json = gson.toJson(receitaAtualizada);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL + "/" + id))
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
           if (response.statusCode() == 200) { 
            JOptionPane.showMessageDialog(null, "Receita atualizada com sucesso!");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, 
                "Erro ao atualizar receita. Código: " + response.statusCode() + "\nDetalhe: " + response.body(),
                "Erro da API",
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar receita: " + e.getMessage());
            return false;
        }
    }
    
    public boolean deletarReceita(int id) {
        String url = API_URL + "/" + id;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .DELETE()
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                
                JOptionPane.showMessageDialog(null, response.body());
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao deletar receita: " + response.body(), "Erro da API", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (IOException | InterruptedException e) {
            JOptionPane.showMessageDialog(null, "Erro de conexão: " + e.getMessage());
            return false;
        }
    }
    
     public boolean deletarVariasReceitas(List<Integer> ids) {
        String url = API_URL + "/multipleReceitas"; 
        try {
            String jsonBody = gson.toJson(ids);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .method("DELETE", BodyPublishers.ofString(jsonBody))
                    .build();
            
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            
            if (response.statusCode() == 200) {
              
                Map<String, Object> successResponse = gson.fromJson(response.body(), new TypeToken<Map<String, Object>>() {}.getType());
                JOptionPane.showMessageDialog(null, successResponse.get("mensagem").toString());
                return true;
            } else {
              
                JOptionPane.showMessageDialog(null, 
                    "Erro ao deletar receitas. Código: " + response.statusCode() + "\nResposta do Servidor: " + response.body(), 
                    "Erro da API", 
                    JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (IOException | InterruptedException e) {
            JOptionPane.showMessageDialog(null, "Erro de conexão: " + e.getMessage());
            return false;
        }
    }


}
    
    


