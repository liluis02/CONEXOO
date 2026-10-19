/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.data.CONEXOO.CONEXOO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    public static Connection conectar() throws SQLException {

        String url = "jdbc:mysql://localhost:3306/conexoo";
        String usuario = "root";
        String senha = "123456";

        return DriverManager.getConnection(url, usuario, senha);
    }
    public static void main(String[] args) {
    try (Connection Conexao = conectar()) {
        System.out.println("Conexão com MySQL realizada com sucesso!");
    } catch (SQLException e) {
        System.out.println("Erro ao conectar: " + e.getMessage());
    }
}
}



