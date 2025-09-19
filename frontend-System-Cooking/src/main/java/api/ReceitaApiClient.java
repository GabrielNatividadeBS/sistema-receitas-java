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
                return gson.fromJson(response.body(), new TypeToken<List<Receita>>() {}.getType());
            } else {
                JOptionPane.showMessageDialog(null, "Não foi possível buscar as receitas. Erro da API: " + response.statusCode());
                return Collections.emptyList();
            }
        } catch (IOException | InterruptedException e) {
            JOptionPane.showMessageDialog(null, "Erro de conexão: Não foi possível conectar ao servidor do back-end.");
            return Collections.emptyList();
        }
    }
}