/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pmriachodoce.SistemaRiachoDoce.dados;

import br.com.pmriachodoce.SistemaRiachoDoce.entidade.Cliente;
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
public class ClienteDAO {
    private static final String SQL_INSERT = "INSERT INTO CLIENTE(CPF, RG, NOME, TELEFONE,ENDERECO) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_BUSCAR_POR_CPF = "SELECT * FROM CLIENTE WHERE CPF = ? AND F = TRUE";
    private static final String SQL_BUSCAR_POR_CPF_CONTA = "SELECT * FROM CLIENTE WHERE CPF = ?";
    private static final String SQL_BUSCAR_TODOS = "SELECT * FROM CLIENTE WHERE F = TRUE";
    private static final String SQL_ATUALIZAR_DADOS = "UPDATE CLIENTE SET RG = ?, NOME = ?, TELEFONE = ?,ENDERECO = ?, STATUS = ? WHERE CPF = ? AND F = TRUE";
    private static final String SQL_DELETAR_POR_CPF = "UPDATE CLIENTE SET F = FALSE WHERE CPF = ?";
    
    public void criar(Cliente cliente) throws SQLException{
        PreparedStatement comando = null;
        Connection conexao = null;    
        try{
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_INSERT);
            comando.setString(1, cliente.getCPF());
            comando.setString(2, cliente.getRG());
            comando.setString(3, cliente.getNome());
            comando.setString(4, cliente.getTelefone());
            comando.setString(5, cliente.getEndereco());    
          
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
    
     public Cliente buscarByCPF(String cpf) throws SQLException{
        Cliente cliente = null;
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        try{
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_BUSCAR_POR_CPF);
            comando.setString(1, cpf);
            resultado = comando.executeQuery();
            
            while(resultado.next()){
                cliente = new Cliente();
                cliente.setCPF(resultado.getString(1));
                cliente.setRG(resultado.getString(2));
                cliente.setNome(resultado.getString(3));
                cliente.setTelefone(resultado.getString(4));
                cliente.setEndereco(resultado.getString(5));
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
        
        return cliente;
    }
     
    public List<Cliente> buscarTodos() throws SQLException{
        List<Cliente> clientes = new ArrayList<>();
        Cliente cliente = null;
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        try{
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_BUSCAR_TODOS);
            resultado = comando.executeQuery();
            
            while(resultado.next()){
                cliente = new Cliente();
                cliente.setCPF(resultado.getString(1));
                cliente.setRG(resultado.getString(2));
                cliente.setNome(resultado.getString(3));
                cliente.setTelefone(resultado.getString(5));
                cliente.setEndereco(resultado.getString(6));
                clientes.add(cliente);
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
        return clientes;
    }
    
    public void atualizarDados(Cliente cliente) throws SQLException{
        PreparedStatement comando = null;
        Connection conexao = null;    
        try{
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_ATUALIZAR_DADOS);
            comando.setString(7, cliente.getCPF());
            comando.setString(1, cliente.getRG());
            comando.setString(2, cliente.getNome());
            comando.setString(4, cliente.getTelefone());
            comando.setString(5, cliente.getEndereco());    
            
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
    
    public void desativarCliente(Cliente cliente) throws SQLException{
        Connection conexao = null;
        PreparedStatement comando = null;
        
        try {
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_DELETAR_POR_CPF);
            comando.setString(1, cliente.getCPF());
            comando.execute();
            conexao.commit();
        } catch (Exception e) {
            if (conexao != null) {
                conexao.rollback();
            }
            throw new RuntimeException(e);
        } finally {
            if (comando != null && !comando.isClosed()) {
                comando.close();
            }
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
            }
        }
    }
    
    public Cliente buscarByCPFConta(String cpf) throws SQLException{
        Cliente cliente = null;
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        try{
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_BUSCAR_POR_CPF_CONTA);
            comando.setString(1, cpf);
            resultado = comando.executeQuery();
            
            while(resultado.next()){
                cliente = new Cliente();
                cliente.setCPF(resultado.getString(1));
                cliente.setRG(resultado.getString(2));
                cliente.setNome(resultado.getString(3));
                cliente.setTelefone(resultado.getString(4));
                cliente.setEndereco(resultado.getString(5));             
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
        
        return cliente;
    }
}
