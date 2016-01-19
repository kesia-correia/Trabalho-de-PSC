/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pmriachodoce.SistemaRiachoDoce.negocio;

import br.com.pmriachodoce.SistemaRiachoDoce.dados.ClienteDAO;
import br.com.pmriachodoce.SistemaRiachoDoce.entidade.Cliente;
import br.com.pmriachodoce.SistemaRiachoDoce.excecoes.CPFCadastradoException;
import br.com.pmriachodoce.SistemaRiachoDoce.excecoes.ClienteInexistenteException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Késia Correia
 */
public class ClienteBO {
    public void criar(Cliente cliente) throws SQLException{
      ClienteDAO clienteDAO = new ClienteDAO();  
      clienteDAO.criar(cliente);
    }
    
    public void verificaUsuarioExistente(Cliente cliente) throws SQLException{
        Cliente clienteExistente = null;
        ClienteDAO clienteDAO = new ClienteDAO();
        clienteExistente = clienteDAO.buscarByCPF(cliente.getCPF());
        if(clienteExistente != null){
            throw new CPFCadastradoException("Atenção: Já existe um cliente cadastrado com esse CPF! ");
        }
    }
    
    public List<Cliente> buscarTodos() throws SQLException{
        List<Cliente> clientes = null;
        ClienteDAO clienteDAO = new ClienteDAO();
        clientes = clienteDAO.buscarTodos();
        return clientes;
    }
    
    public void atualizarDados(Cliente cliente) throws SQLException{
      ClienteDAO clienteDAO = new ClienteDAO();  
      clienteDAO.atualizarDados(cliente);
    }
    
     public void deletarCliente(Cliente cliente) throws SQLException {
        ClienteDAO clienteDAO = new ClienteDAO();
        Cliente clienteExiste = null;
        clienteExiste = clienteDAO.buscarByCPF(cliente.getCPF());
        if (clienteExiste != null) {
            clienteDAO.desativarCliente(cliente);
        } else {
            throw new ClienteInexistenteException("Impossível excluir cliente (Cliente não encontrado)!");
        }
    }
    
    public Cliente buscarCpf(String CPF) throws SQLException{
        ClienteDAO clienteDAO = new ClienteDAO();
        Cliente cliente = null;
        cliente = clienteDAO.buscarByCPF(CPF);
        if(cliente == null){
            throw new ClienteInexistenteException ("Cliente não encontrado! ");
        }
        return cliente;
    }
}
