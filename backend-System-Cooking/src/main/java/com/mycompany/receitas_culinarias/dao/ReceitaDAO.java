package com.mycompany.receitas_culinarias.dao;

import com.mycompany.receitas_culinarias.model.Receita;
import com.mycompany.receitas_culinarias.conexao.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReceitaDAO {
    public void inserir(Receita r) {
        Connection con = Conexao.getConexao();
        try {
            PreparedStatement stmt = con.prepareStatement(
                "INSERT INTO receita (nome, ingredientes, modo_preparo, tempo_preparo, categoria) VALUES (?,?,?,?,?)");
            stmt.setString(1, r.getNome());
            stmt.setString(2, r.getIngredientes());
            stmt.setString(3, r.getModoPreparo());
            stmt.setInt(4, r.getTempoPreparo());
            stmt.setString(5, r.getCategoria());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Receita> listar() {
        List<Receita> receitas = new ArrayList<>();
        Connection con = Conexao.getConexao();
        try {
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM receita");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Receita r = new Receita();
                r.setId(rs.getInt("id"));
                r.setNome(rs.getString("nome"));
                r.setIngredientes(rs.getString("ingredientes"));
                r.setModoPreparo(rs.getString("modo_preparo"));
                r.setTempoPreparo(rs.getInt("tempo_preparo"));
                r.setCategoria(rs.getString("categoria_id"));
                receitas.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return receitas;
    }
    
    public Receita buscarPorId(int id) {
        Receita r = null;
        Connection con = Conexao.getConexao();
            try {
                PreparedStatement stmt = con.prepareStatement("SELECT * FROM receita WHERE id = ?");
                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                r = new Receita();
                r.setId(rs.getInt("id"));
                r.setNome(rs.getString("nome"));
                r.setIngredientes(rs.getString("ingredientes"));
                r.setModoPreparo(rs.getString("modo_preparo"));
                r.setTempoPreparo(rs.getInt("tempo_preparo"));
                r.setCategoria(rs.getString("categoria_id")); // ajuste se for categoria_id
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return r;
    }
    
    public void atualizar(Receita r) {
        Connection con = Conexao.getConexao();
            try {
                PreparedStatement stmt = con.prepareStatement(
                "UPDATE receita SET nome=?, ingredientes=?, modo_preparo=?, tempo_preparo=?, categoria=? WHERE id=?");
                stmt.setString(1, r.getNome());
                stmt.setString(2, r.getIngredientes());
                stmt.setString(3, r.getModoPreparo());
                stmt.setInt(4, r.getTempoPreparo());
                stmt.setString(5, r.getCategoria());
                stmt.setInt(6, r.getId());
                stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } 
}
