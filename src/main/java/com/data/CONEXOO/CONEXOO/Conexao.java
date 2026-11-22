package com.data.CONEXOO.CONEXOO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    public static Connection conectar() throws SQLException {

        String url = "jdbc:postgresql://100.108.167.39:5432/Conexoo";
        String usuario = "postgres";
        String senha = "setesaias13";

        return DriverManager.getConnection(url, usuario, senha);
    }

    public static void main(String[] args) {
        try (Connection conexao = conectar()) {
            System.out.println("Conexão com PostgreSQL realizada com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao conectar: " + e.getMessage());
        }
    }
}