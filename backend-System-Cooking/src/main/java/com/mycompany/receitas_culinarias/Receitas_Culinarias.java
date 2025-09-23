package com.mycompany.receitas_culinarias;

import com.mycompany.receitas_culinarias.controller.ReceitaController;
import com.mycompany.receitas_culinarias.controller.CategoriaController;
import static spark.Spark.*;

public class Receitas_Culinarias {
    public static void main(String[] args) {
        
        port(8080);
        
        
        ReceitaController.rotas();
        CategoriaController.rotas();
        System.out.println("Servidor da API de Receitas iniciado em http://localhost:8080");
    }
}