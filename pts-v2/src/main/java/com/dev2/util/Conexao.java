/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev2.util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Douglas
 */
public class Conexao {
    public static Connection abrirConexao() throws Exception{
        Connection conexao;
        Class.forName("com.mysql.jdbc.Driver");
        
        conexao = DriverManager.getConnection("jdbc:mysql://localhost/projetodevsisv2", "root", "");
        
        return conexao;
    }
}