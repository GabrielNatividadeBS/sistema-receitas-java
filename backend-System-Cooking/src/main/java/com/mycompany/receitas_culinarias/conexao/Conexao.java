package com.mycompany.receitas_culinarias.conexao;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexao {

   
    private static final Dotenv dotenv = Dotenv.configure().filename(".env.prod").load();

    public static Connection getConexao() {
        try {
         
            String endereco = dotenv.get("DB_URL");
            String usuario = dotenv.get("DB_USER");
            String senha = dotenv.get("DB_PASS");

         
            if (endereco == null || usuario == null || senha == null) {
                throw new RuntimeException("Não foi possível carregar as variáveis de ambiente do arquivo .env. Verifique se o arquivo existe na raiz do projeto e contém as chaves DB_URL, DB_USER e DB_PASS.");
            }

            return DriverManager.getConnection(endereco, usuario, senha);

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Erro ao estabelecer uma conexao com o banco", ex);
        }
    }


    public static void fecharConexao(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao fechar uma conexao com o banco", ex);
        }
    }

    public static void fecharConexao(Connection con, PreparedStatement stmt) {
        fecharConexao(con);
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao fechar uma conexao com o banco", ex);
        }
    }

    public static void fecharConexao(Connection con, PreparedStatement stmt, ResultSet rs) {
        fecharConexao(con, stmt);
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao fechar uma conexao com o banco", ex);
        }
    }
}