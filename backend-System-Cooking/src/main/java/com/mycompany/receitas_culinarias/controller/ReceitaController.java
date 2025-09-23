package com.mycompany.receitas_culinarias.controller;

import com.google.gson.Gson;
import com.mycompany.receitas_culinarias.dao.ReceitaDAO;
import com.mycompany.receitas_culinarias.model.Receita;
import java.util.HashMap;
import static spark.Spark.*;
import java.util.List;
import com.google.gson.reflect.TypeToken;
import java.util.Map;

public class ReceitaController {

    public static void rotas() {
        ReceitaDAO dao = new ReceitaDAO();
        Gson gson = new Gson();

         get("/receitas", (req, res) -> {
            res.type("application/json");
            
            return gson.toJson(dao.listar(null));
        });

  post("/receitas", (req, res) -> {
    try {
        Receita r = gson.fromJson(req.body(), Receita.class);
        
       
        Receita receitaSalva = dao.inserir(r); 
        
        res.status(201); // 201 Created
        res.type("application/json");
        return gson.toJson(receitaSalva); 
    } catch (Exception e) {
        res.status(400); 
        return "Erro ao salvar receita: " + e.getMessage();
    }
});


           
        put("/receitas/:id", (req, res) -> {
            try {
            int id = Integer.parseInt(req.params("id"));
            Receita r = gson.fromJson(req.body(), Receita.class);
            r.setId(id);
            dao.atualizar(r);
            res.status(200);
            return "Receita atualizada!";
            } catch(Exception e){
            res.status(400);
            return "Erro ao atualizar Receita";
            }
          
        });
         
 delete("/receitas/:id", (req, res) -> {
    try {
        
        int id = Integer.parseInt(req.params(":id"));
        boolean sucesso = dao.deletar(id);

        if (sucesso) {
            res.status(200);
            return "Receita deletada com sucesso!";
        } else {
            res.status(404); 
            return "Receita com o ID especificado não foi encontrada.";
        }
    } catch (NumberFormatException e) {
        res.status(400); 
        return "O ID fornecido na URL é inválido. Deve ser um número.";
    } catch (Exception e) {
        res.status(500); 
        return "Ocorreu um erro inesperado no servidor ao tentar deletar a receita.";
    }
});
 


    }
}
