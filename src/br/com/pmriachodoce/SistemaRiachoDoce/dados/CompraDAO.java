/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pmriachodoce.SistemaRiachoDoce.dados;

import br.com.pmriachodoce.SistemaRiachoDoce.entidade.ItensCompra;
import br.com.pmriachodoce.SistemaRiachoDoce.entidade.Compra;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Késia Correia
 */
public class CompraDAO {
   private static String SQL_INSERT = "INSERT INTO COMPRA(DATA, VALORTOTAL, USUARIO, CLIENTE)  VALUES(?, ?, ?, ?)";
    private static String SQL_BUSCAR_TODOS = "SELECT * FROM COMPRA";
   
    
    public void inserir(Compra compra) throws SQLException{
        PreparedStatement comando = null;
        Connection conexao = null;
        ResultSet resultado = null;
        try{
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
            java.sql.Date data = new java.sql.Date(compra.getDataCompra().getTime());
            comando.setDate(1, data);
            comando.setDouble(2, compra.getValorTotal());
            comando.setString(3, compra.getUsuario().getCPF());
            comando.setString(4, compra.getCliente().getCPF());
            comando.execute();
            resultado = comando.getGeneratedKeys();
            while (resultado.next()) {
                compra.setNumeroCompra(resultado.getInt(1));
            }
            
            ItensCompraDAO itensCompraDAO = new ItensCompraDAO();
            for (ItensCompra item : compra.getItensCompra()) {
                itensCompraDAO.criar(conexao, compra.getNumeroCompra(), item);
            }
            
            ComprasRealizadasClientesDAO contaDAO = new ComprasRealizadasClientesDAO();
            contaDAO.inserir(conexao, compra);
            conexao.commit();
        } catch (Exception e) {
            if (conexao != null) {
                conexao.rollback();
            }
            throw new RuntimeException();
        } finally {
            if (comando != null && !comando.isClosed()) {
                comando.close();
            }
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
            }
        }
    }
    
}
