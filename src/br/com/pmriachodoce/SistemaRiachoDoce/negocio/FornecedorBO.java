/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pmriachodoce.SistemaRiachoDoce.negocio;

import br.com.pmriachodoce.SistemaRiachoDoce.dados.FornecedorDAO;
import br.com.pmriachodoce.SistemaRiachoDoce.entidade.Fornecedor;
import br.com.pmriachodoce.SistemaRiachoDoce.excecoes.CNPJCadastradoException;
import br.com.pmriachodoce.SistemaRiachoDoce.excecoes.FornecedorInexistenteException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Késia Correia
 */
public class FornecedorBO {
    public void criar(Fornecedor fornecedor) throws SQLException{
      FornecedorDAO fornecedorDAO = new FornecedorDAO();  
      fornecedorDAO.criar(fornecedor);
    }
    
    public void verificarCNPJ(Fornecedor fornecedor) throws SQLException{
        Fornecedor CNPJExistente = null;
        FornecedorDAO fornecedorDAO = new FornecedorDAO();
        CNPJExistente = fornecedorDAO.buscarPorCNPJ(fornecedor.getCNPJ());
        if(CNPJExistente != null){
            throw new CNPJCadastradoException("Atenção: Já existe um fornecedor cadastrado com esse CNPJ ");
        }
    }


    public List<Fornecedor> buscarTodos() throws SQLException{
        List<Fornecedor> fornecedores = null;
        FornecedorDAO fornecedorDAO = new FornecedorDAO();
        fornecedores = fornecedorDAO.buscarTodos();
        return fornecedores;
    }
    
    public void atualizarDados(Fornecedor fornecedor) throws SQLException{
      FornecedorDAO fornecedorDAO = new FornecedorDAO();  
      fornecedorDAO.atualizarDados(fornecedor);
    }
    
    public void verificaUsuarioExistente(Fornecedor fornecedor) throws SQLException{
        Fornecedor fornecedorExistente = null;
        FornecedorDAO fornecedorDAO = new FornecedorDAO();
        fornecedorExistente = fornecedorDAO.buscarPorCNPJ(fornecedor.getCNPJ());
   }
    
    public void deletarFornecedor(Fornecedor fornecedor) throws SQLException {
        FornecedorDAO fornecedorDAO = new FornecedorDAO();
        Fornecedor fornecedorExiste = null;
        fornecedorExiste = fornecedorDAO.buscarPorCNPJ(fornecedor.getCNPJ());
        if (fornecedorExiste != null) {
            fornecedorDAO.deletarFornecedor(fornecedor);
        } else {
            throw new FornecedorInexistenteException("Fornecedor não encontrado!");
        }
    }
    
    public Fornecedor buscarCNPJ(String CNPJ) throws SQLException{
        FornecedorDAO fornecedorDAO = new FornecedorDAO();
        Fornecedor fornecedor = null;
        fornecedor = fornecedorDAO.buscarPorCNPJ(CNPJ);
        if(fornecedor == null){
            throw new FornecedorInexistenteException ("Fornecedor não encontrado! ");
        }
        return fornecedor;
    }
}
