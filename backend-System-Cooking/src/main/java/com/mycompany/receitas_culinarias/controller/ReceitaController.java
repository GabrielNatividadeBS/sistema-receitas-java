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


           // Editar receita
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
         //Deletar receita
 delete("/receitas/:id", (req, res) -> {
    try {
        // Pega o parâmetro ':id' da URL e converte para inteiro
        int id = Integer.parseInt(req.params(":id"));
        boolean sucesso = dao.deletar(id);

        if (sucesso) {
            res.status(200);
            return "Receita deletada com sucesso!";
        } else {
            res.status(404); // HTTP Status: Not Found
            return "Receita com o ID especificado não foi encontrada.";
        }
    } catch (NumberFormatException e) {
        res.status(400); // HTTP Status: Bad Request
        return "O ID fornecido na URL é inválido. Deve ser um número.";
    } catch (Exception e) {
        res.status(500); // HTTP Status: Internal Server Error
        return "Ocorreu um erro inesperado no servidor ao tentar deletar a receita.";
    }
});
 
// delete("/multipleReceitas", (req, res) -> {;
//    res.type("application/json");
//    Map<String, Object> resposta = new HashMap<>();
//
//    try {
//        // Pega o corpo da requisição (o JSON)
//        String body = req.body();
//        
//        // Usa o Gson para converter a string JSON em uma Lista de Inteiros
//        TypeToken<List<Integer>> token = new TypeToken<List<Integer>>() {};
//        List<Integer> idsParaDeletar = gson.fromJson(body, token.getType());
//
//        if (idsParaDeletar == null || idsParaDeletar.isEmpty()) {
//            res.status(400); // Bad Request
//            resposta.put("status", "erro");
//            resposta.put("mensagem", "A lista de IDs para deleção não pode ser vazia.");
//            return gson.toJson(resposta);
//        }
//
//        int numDeletados = dao.deletarVarios(idsParaDeletar);
//
//        if (numDeletados > 0) {
//            resposta.put("status", "sucesso");
//            resposta.put("mensagem", numDeletados + " receita(s) deletada(s) com sucesso!");
//            resposta.put("totalDeletado", numDeletados);
//            return gson.toJson(resposta);
//        } else {
//            res.status(404); // Not Found
//            resposta.put("status", "erro");
//            resposta.put("mensagem", "Nenhuma receita foi encontrada com os IDs fornecidos.");
//            return gson.toJson(resposta);
//        }
//
//    } catch (Exception e) {
//        res.status(500); // Internal Server Error
//        resposta.put("status", "erro");
//        resposta.put("mensagem", "Ocorreu um erro inesperado no servidor.");
//        return gson.toJson(resposta);
//    }
//});

    }
}
