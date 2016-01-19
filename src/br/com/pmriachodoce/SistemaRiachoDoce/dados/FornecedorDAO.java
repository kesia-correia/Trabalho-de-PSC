/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pmriachodoce.SistemaRiachoDoce.dados;

import br.com.pmriachodoce.SistemaRiachoDoce.entidade.Fornecedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Késia Correia
 */
public class FornecedorDAO {
    private static final String SQL_INSERT = "INSERT INTO FORNECEDOR(CNPJ, NOME,TELEFONE, DESCRICAO, ENDERECO) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_BUSCAR_POR_CNPJ = "SELECT * FROM FORNECEDOR WHERE CNPJ = ? AND F = TRUE";
    private static final String SQL_BUSCAR_TODOS = "SELECT * FROM FORNECEDOR WHERE F = TRUE";
    private static final String SQL_ATUALIZAR_DADOS = "UPDATE FORNECEDOR SET NOME = ?, TELEFONE = ?,DESCRICAO = ?, ENDERECO = ? WHERE CNPJ = ? AND F = TRUE";
    private static final String SQL_DELETAR = "UPDATE FORNECEDOR SET F = FALSE WHERE CNPJ = ?";
    
    public void criar(Fornecedor fornecedor) throws SQLException{
        PreparedStatement comando = null;
        Connection conexao = null;    
        try{
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_INSERT);
            comando.setString(1, fornecedor.getCNPJ());
            comando.setString(2, fornecedor.getNome()); 
            comando.setString(3, fornecedor.getTelefone());
            comando.setString(4, fornecedor.getEndereco());   
            comando.setString(5, fornecedor.getDescricao());
                     
            comando.execute();
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
   
  
     public Fornecedor buscarPorCNPJ(String CNPJ) throws SQLException{
        Fornecedor fornecedor = null;
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        try{
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_BUSCAR_POR_CNPJ);
            comando.setString(1, CNPJ);
            resultado = comando.executeQuery();
            
            while(resultado.next()){
                fornecedor = new Fornecedor();
                fornecedor.setCNPJ(resultado.getString(1));
                fornecedor.setNome(resultado.getString(2));
                fornecedor.setTelefone(resultado.getString(3));
                fornecedor.setEndereco(resultado.getString(4));
                fornecedor.setDescricao(resultado.getString(5));
            }
             
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
        
        return fornecedor;
    }
     
    public List<Fornecedor> buscarTodos() throws SQLException{
        List<Fornecedor> fornecedores = new ArrayList<>();
        Fornecedor fornecedor = null;
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        try{
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_BUSCAR_TODOS);
            resultado = comando.executeQuery();
            
            while(resultado.next()){
                fornecedor = new Fornecedor();
                fornecedor.setCNPJ(resultado.getString(1));
                fornecedor.setNome(resultado.getString(2));
                fornecedor.setTelefone(resultado.getString(3));
                fornecedor.setEndereco(resultado.getString(4));
                fornecedor.setDescricao(resultado.getString(5));
                
            }
            
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
        return fornecedores;
    }
    
    public void atualizarDados(Fornecedor fornecedor) throws SQLException{
        PreparedStatement comando = null;
        Connection conexao = null;    
        try{
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_ATUALIZAR_DADOS);
            comando.setString(5, fornecedor.getCNPJ());
            comando.setString(1, fornecedor.getNome());
            comando.setString(2, fornecedor.getTelefone());
            comando.setString(3, fornecedor.getEndereco());   
            comando.setString(4, fornecedor.getDescricao());
                        
            comando.execute();
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
    
    public void deletarFornecedor(Fornecedor fornecedor) throws SQLException {
        PreparedStatement comando = null;
        Connection conexao = null;
        try {
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_DELETAR);
            comando.setString(1, fornecedor.getCNPJ());
            comando.execute();
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
