package com.mycompany.receitas_culinarias.dao;

import com.mycompany.receitas_culinarias.model.Receita;
import com.mycompany.receitas_culinarias.conexao.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;

public class ReceitaDAO {

 
    private int getCategoriaIdPeloNome(String nomeCategoria, Connection con) throws SQLException {
        String sql = "SELECT id FROM categoria WHERE nome = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, nomeCategoria);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                } else {
                 
                    throw new SQLException("Categoria não encontrada: " + nomeCategoria);
                }
            }
        }
    }

 public Receita inserir(Receita r) {
    String sql = "INSERT INTO receita (nome, ingredientes, modo_preparo, tempo_preparo, categoria_id) VALUES (?, ?, ?, ?, ?)";
    
    try (Connection con = Conexao.getConexao()) {
        int categoriaId = getCategoriaIdPeloNome(r.getCategoria(), con);

        // Usamos a flag RETURN_GENERATED_KEYS para pegar o ID que o banco vai criar
        try (PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, r.getNome());
            stmt.setString(2, r.getIngredientes());
            stmt.setString(3, r.getModoPreparo());
            stmt.setInt(4, r.getTempoPreparo());
            stmt.setInt(5, categoriaId);
            
            stmt.executeUpdate();
            
            // Pega o ID gerado pelo banco
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    r.setId(generatedKeys.getInt(1)); // Define o ID no objeto
                } else {
                    throw new SQLException("Falha ao obter o ID da receita criada.");
                }
            }
        }
    } catch (SQLException e) {
        throw new RuntimeException("Erro ao inserir receita no banco: ", e);
    }
    return r; 
}
 
 
    public List<Receita> listar(Integer categoriaId) {
        List<Receita> receitas = new ArrayList<>();
        
        String sql = "SELECT r.id, r.nome, r.ingredientes, r.modo_preparo, r.tempo_preparo, c.nome AS categoria_nome " +
                     "FROM receita r " +
                     "JOIN categoria c ON r.categoria_id = c.id";

        if (categoriaId != null && categoriaId > 0) {
            sql += " WHERE r.categoria_id = ?";
        }

        try (Connection con = Conexao.getConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            if (categoriaId != null && categoriaId > 0) {
                stmt.setInt(1, categoriaId);
            }
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Receita r = new Receita();
                r.setId(rs.getInt("id"));
                r.setNome(rs.getString("nome"));
                r.setIngredientes(rs.getString("ingredientes"));
                r.setModoPreparo(rs.getString("modo_preparo"));
                r.setTempoPreparo(rs.getInt("tempo_preparo"));
                
                r.setCategoria(rs.getString("categoria_nome")); 
                receitas.add(r);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar receitas: " + e.getMessage(), e);
        }
        return receitas;
    }

    public Receita buscarPorId(int id) {
        Receita r = null;
       
        String sql = "SELECT r.id, r.nome, r.ingredientes, r.modo_preparo, r.tempo_preparo, c.nome AS categoria_nome " +
                     "FROM receita r " +
                     "JOIN categoria c ON r.categoria_id = c.id " +
                     "WHERE r.id = ?";
        
        try (Connection con = Conexao.getConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                r = new Receita();
                r.setId(rs.getInt("id"));
                r.setNome(rs.getString("nome"));
                r.setIngredientes(rs.getString("ingredientes"));
                r.setModoPreparo(rs.getString("modo_preparo"));
                r.setTempoPreparo(rs.getInt("tempo_preparo"));
                r.setCategoria(rs.getString("categoria_nome"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar receita por ID: " + e.getMessage(), e);
        }
        return r;
    }

    public void atualizar(Receita r) {
       
        String sql = "UPDATE receita SET nome=?, ingredientes=?, modo_preparo=?, tempo_preparo=?, categoria_id=? WHERE id=?";
        
        try (Connection con = Conexao.getConexao()) {
           
            int categoriaId = getCategoriaIdPeloNome(r.getCategoria(), con);
            
            
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setString(1, r.getNome());
                stmt.setString(2, r.getIngredientes());
                stmt.setString(3, r.getModoPreparo());
                stmt.setInt(4, r.getTempoPreparo());
                stmt.setInt(5, categoriaId); 
                stmt.setInt(6, r.getId());
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar receita: " + e.getMessage(), e);
        }
    }

}