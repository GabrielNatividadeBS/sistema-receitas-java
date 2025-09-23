package com.mycompany.receitas_culinarias.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author cleberlira
 */
public class Conexao {

    private static final String DRIVE_MYSQL = "com.mysql.cj.jdbc.Driver";
    private static final String ENDERECO = "jdbc:mysql://localhost:3306/receitas_db";
    private static final String USUARIO = "root";
    private static final String SENHA = "cajazeiras9926";
    public static Connection getConexao() {

        try {
            Class.forName(DRIVE_MYSQL);
            Connection conn
                    = DriverManager.getConnection(ENDERECO, USUARIO, SENHA);
            return conn;

        } catch (ClassNotFoundException | SQLException ex) {
           
            ex.printStackTrace();

            throw new RuntimeException("Erro ao estabelecer uma conexao com o banco");
        }
    }

    public static void fecharConexao(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao fechar uma conexao com o banco");
        }

    }

    public static void fecharConexao(Connection con, PreparedStatement stmt) {
        fecharConexao(con);

        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao fechar uma conexao com o banco");
        }

    }

    public static void fecharConexao(Connection con, PreparedStatement stmt, ResultSet rs) {
        fecharConexao(con, stmt);

        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao fechar uma conexao com o banco");
        }

    }

}
