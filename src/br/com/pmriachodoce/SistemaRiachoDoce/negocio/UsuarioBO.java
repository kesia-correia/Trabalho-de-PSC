/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pmriachodoce.SistemaRiachoDoce.negocio;

import br.com.pmriachodoce.SistemaRiachoDoce.dados.UsuarioDAO;
import br.com.pmriachodoce.SistemaRiachoDoce.entidade.Usuario;
import br.com.pmriachodoce.SistemaRiachoDoce.excecoes.CPFCadastradoException;
import br.com.pmriachodoce.SistemaRiachoDoce.excecoes.InvalidoException;
import br.com.pmriachodoce.SistemaRiachoDoce.excecoes.LoginExistenteException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Késia Correia
 */
public class UsuarioBO {
     public void criar(Usuario usuario) throws SQLException {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.inserir(usuario);
    }

    public void verificaUsuarioExistente(Usuario usuario) throws SQLException {
        Usuario usuarioExistente = null;
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioExistente = usuarioDAO.buscarPeloCPF(usuario.getCPF());
        if (usuarioExistente != null) {
            throw new CPFCadastradoException("Existe um usuario cadastrado com esse CPF.");
        }
    }

    public void verificaUsuarioLogin(Usuario usuario) throws SQLException {
        Usuario usuarioExistente = null;
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioExistente = usuarioDAO.buscarPeloLogin(usuario.getLogin());
        if (usuarioExistente != null) {
            throw new LoginExistenteException("Existe um usuario cadastrado com esse login.");
        }
    }

    public List<Usuario> buscarTodos() throws SQLException {
        List<Usuario> usuarios = null;
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarios = usuarioDAO.buscarTodos();
        return usuarios;
    }

    public void atualizarDados(Usuario usuario) throws SQLException {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.atualizarDados(usuario);
    }

    public void deletarUsuario(Usuario usuario) throws SQLException {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuarioExiste = null;
        usuarioExiste = usuarioDAO.buscarPeloCPF(usuario.getCPF());
        if (usuarioExiste != null) {
            usuarioDAO.deletarUsuario(usuario);
        } else {
            throw new InvalidoException("Impossível excluir usuário. Este não se encontra em nossa base de dados");
        }
    }
    
    public Usuario login(String login, String senha) throws SQLException {
        Usuario usuarioExistente = null;
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioExistente = usuarioDAO.buscarPeloLoginESenha(login, senha);
        if (usuarioExistente != null) {
            return usuarioExistente;
        }
       // }else{
         //   throw new InvalidoException("LOGIN ou SENHA inválidos! ");
       // }
         return null;
    }
    
    public void atualizarDadosSenha(Usuario usuario) throws SQLException {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.atualizarDados(usuario);
    }
}
