package com.mycompany.receitas_culinarias;

import com.mycompany.receitas_culinarias.controller.ReceitaController;
import static spark.Spark.*;

public class Receitas_Culinarias {
    public static void main(String[] args) {
        // Define a porta em que o servidor vai rodar
        port(8080);
        
        // Configura as rotas da API
        ReceitaController.rotas();
        
        System.out.println("Servidor da API de Receitas iniciado em http://localhost:8080");
    }
}