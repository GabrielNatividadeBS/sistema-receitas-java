package com.mycompany.receitas_culinarias.dao;

import com.mycompany.receitas_culinarias.conexao.Conexao;
import com.mycompany.receitas_culinarias.model.Categoria;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {
    public List<Categoria> listarTodas() {
        List<Categoria> categorias = new ArrayList<>();
        String sql = "SELECT * FROM categoria ORDER BY nome";
        
        try (Connection con = Conexao.getConexao();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Categoria c = new Categoria();
                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                categorias.add(c);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar categorias: ", e);
        }
        return categorias;
    }
}