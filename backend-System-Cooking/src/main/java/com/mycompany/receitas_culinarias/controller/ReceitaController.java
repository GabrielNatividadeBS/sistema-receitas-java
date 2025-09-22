package com.mycompany.receitas_culinarias.controller;

import com.google.gson.Gson;
import com.mycompany.receitas_culinarias.dao.ReceitaDAO;
import com.mycompany.receitas_culinarias.model.Receita;

import static spark.Spark.*;

public class ReceitaController {
    public static void rotas() {
        ReceitaDAO dao = new ReceitaDAO();
        Gson gson = new Gson();

        // Listar receitas
        get("/receitas", (req, res) -> {
            res.type("application/json");
            return gson.toJson(dao.listar());
        });

        // Inserir receita
        post("/receitas", (req, res) -> {
            Receita r = gson.fromJson(req.body(), Receita.class);
            dao.inserir(r);
            res.status(201);
            return "Receita adicionada!";
        });
         //Deletar receita
  delete("/receitas/:id", (req, res) -> {
    int id = Integer.parseInt(req.params(":id"));

    boolean sucesso = dao.deletar(id);
    if (sucesso) {
        res.status(200);
        return "Receita deletada com sucesso!";
    } else {
        res.status(404);
        return "Receita não encontrada.";
    }
});
    }

}
