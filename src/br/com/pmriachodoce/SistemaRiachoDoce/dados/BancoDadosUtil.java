/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pmriachodoce.SistemaRiachoDoce.dados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Késia Correia
 */
public class BancoDadosUtil {
    private static final String DRIVER = "org.hsqldb.jdbcDriver";
    private static final String URL = "jdbc:hsqldb:file:BD/BD_PMRIACHODOCE;shutdown=true";
    private static final String USUARIO = "root";
    private static final String SENHA = "root";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            //Ativa o driver na memória
            Class.forName(DRIVER).newInstance();
            //Cria a conexão com o banco de dados
            connection = DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return connection;
    }    
}
