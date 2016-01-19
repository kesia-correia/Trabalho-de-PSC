/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pmriachodoce.SistemaRiachoDoce.dados;

import br.com.pmriachodoce.SistemaRiachoDoce.entidade.Usuario;
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
public class UsuarioDAO {
    private static final String SQL_INSERT = "INSERT INTO USUARIO(CPF, RG, NOME, TELEFONE, ENDERECO, LOGIN, SENHA) VALUES (?, ?, ?, ?, ?, ?,?)";
    private static final String SQL_BUSCAR_POR_CPF = "SELECT * FROM USUARIO WHERE CPF = ? AND F = TRUE";
    private static final String SQL_BUSCAR_POR_LOGIN = "SELECT * FROM USUARIO WHERE LOGIN = ?";
    private static final String SQL_BUSCAR_TODOS = "SELECT * FROM USUARIO WHERE F = TRUE";
    private static final String SQL_ATUALIZAR_DADOS = "UPDATE USUARIO SET RG = ?, NOME = ?, IDADE = ?, TELEFONE = ?, ENDERECO = ? WHERE CPF = ?";
    private static final String SQL_DELETAR_POR_CPF = "UPDATE USUARIO SET F = FALSE WHERE CPF = ?";
    private static final String SQL_BUSCAR_POR_LOGIN_E_SENHA = "SELECT * FROM USUARIO WHERE LOGIN = ? AND SENHA = ? AND F = TRUE";
    private static final String SQL_ATUALIZAR_SENHA = "UPDATE USUARIO SET SENHA = ? WHERE CPF = ?";
    
    public void inserir(Usuario usuario) throws SQLException{
        PreparedStatement comando = null;
        Connection conexao = null;    
        try{
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_INSERT);
            comando.setString(1, usuario.getCPF());
            comando.setString(2, usuario.getRG());
            comando.setString(3, usuario.getNome());;
            comando.setString(4, usuario.getTelefone());
            comando.setString(5, usuario.getEndereco());
            comando.setString(6, usuario.getLogin());
            comando.setString(7, usuario.getSenha());
      
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
    
    public Usuario buscarPeloCPF(String cpf) throws SQLException{
        Usuario usuario = null;
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        try{
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_BUSCAR_POR_CPF);
            comando.setString(1, cpf);
            resultado = comando.executeQuery();
            
            while(resultado.next()){
                usuario = new Usuario();
                usuario.setCPF(resultado.getString(1));
                usuario.setRG(resultado.getString(2));
                usuario.setNome(resultado.getString(3));
                usuario.setTelefone(resultado.getString(4));
                usuario.setEndereco(resultado.getString(5));
                usuario.setPermissao(resultado.getInt(10));
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
        
        return usuario;
    }
    
    public Usuario buscarPeloLogin(String login) throws SQLException{
        Usuario usuario = null;
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        try{
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_BUSCAR_POR_LOGIN);
            comando.setString(1, login);
            resultado = comando.executeQuery();
            
            while(resultado.next()){
                usuario = new Usuario();
                usuario.setCPF(resultado.getString(1));
                usuario.setRG(resultado.getString(2));
                usuario.setNome(resultado.getString(3));
                usuario.setTelefone(resultado.getString(4));
                usuario.setEndereco(resultado.getString(5));
                usuario.setPermissao(resultado.getInt(10));
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
        return usuario;
    }
    
    
    public List<Usuario> buscarTodos() throws SQLException{
        List<Usuario> usuarios = new ArrayList<>();
        Usuario usuario = null;
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        try{
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_BUSCAR_TODOS);
            resultado = comando.executeQuery();
            
            while(resultado.next()){
                usuario = new Usuario();
                usuario.setCPF(resultado.getString(1));
                usuario.setRG(resultado.getString(2));
                usuario.setNome(resultado.getString(3));
                usuario.setTelefone(resultado.getString(4));
                usuario.setEndereco(resultado.getString(5));
                usuario.setPermissao(resultado.getInt(10));
                usuarios.add(usuario);
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
        return usuarios;
    }
    
    public void atualizarDados(Usuario usuario) throws SQLException{
        PreparedStatement comando = null;
        Connection conexao = null;    
        try{
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_ATUALIZAR_DADOS);
            comando.setString(5, usuario.getCPF());
            comando.setString(1, usuario.getRG());
            comando.setString(2, usuario.getNome());
            comando.setString(3, usuario.getTelefone());
            comando.setString(4, usuario.getEndereco());
            
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
    
    public void deletarUsuario(Usuario usuario) throws SQLException{
        Connection conexao = null;
        PreparedStatement comando = null;
        
        try {
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_DELETAR_POR_CPF);
            comando.setString(1, usuario.getCPF());
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
    
    public Usuario buscarPeloLoginESenha(String login, String senha) throws SQLException{
        Usuario usuario = null;
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        try{
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_BUSCAR_POR_LOGIN_E_SENHA);
            comando.setString(1, login);
            comando.setString(2, senha);
            resultado = comando.executeQuery();
            
            while(resultado.next()){
                usuario = new Usuario();
                usuario.setCPF(resultado.getString(1));
                usuario.setRG(resultado.getString(2));
                usuario.setNome(resultado.getString(3));
                usuario.setTelefone(resultado.getString(4));
                usuario.setEndereco(resultado.getString(5));
                usuario.setPermissao(resultado.getInt(10));
                usuario.setLogin(resultado.getString("LOGIN"));
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
        return usuario;
    }
    
    public void atualizarSenha(Usuario usuario) throws SQLException{
        PreparedStatement comando = null;
        Connection conexao = null;    
        try{
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_ATUALIZAR_SENHA);
            comando.setString(1, usuario.getSenha());
            comando.setString(2, usuario.getCPF());
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
