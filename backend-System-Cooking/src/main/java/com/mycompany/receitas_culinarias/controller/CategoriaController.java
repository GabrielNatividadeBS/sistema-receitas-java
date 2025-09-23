package com.mycompany.receitas_culinarias.controller;

import com.google.gson.Gson;
import com.mycompany.receitas_culinarias.dao.CategoriaDAO;
import static spark.Spark.*;

public class CategoriaController {
    public static void rotas() {
        CategoriaDAO dao = new CategoriaDAO();
        Gson gson = new Gson();
        
        // Rota para buscar todas as categorias
        get("/categorias", (req, res) -> {
            res.type("application/json");
            return gson.toJson(dao.listarTodas());
        });
    }
}