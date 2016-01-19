/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pmriachodoce.SistemaRiachoDoce.negocio;

import br.com.pmriachodoce.SistemaRiachoDoce.dados.CompraDAO;
import br.com.pmriachodoce.SistemaRiachoDoce.entidade.Cliente;
import br.com.pmriachodoce.SistemaRiachoDoce.entidade.Compra;
import br.com.pmriachodoce.SistemaRiachoDoce.entidade.ItensCompra;
import br.com.pmriachodoce.SistemaRiachoDoce.entidade.Usuario;
import br.com.pmriachodoce.SistemaRiachoDoce.excecoes.ClienteDevedorException;
import br.com.pmriachodoce.SistemaRiachoDoce.excecoes.ClienteInexistenteException;
import br.com.pmriachodoce.SistemaRiachoDoce.excecoes.CompraInvalidaException;
import br.com.pmriachodoce.SistemaRiachoDoce.excecoes.QuantidadeItensInvalidaException;
import br.com.pmriachodoce.SistemaRiachoDoce.excecoes.UsuarioInvalidoException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Késia Correia
 */
public class CompraBO {
    public void inserir(Compra compra) throws ClienteInexistenteException, QuantidadeItensInvalidaException,ClienteDevedorException, SQLException {
        if(compra == null){
            throw new CompraInvalidaException("Venda invalida");
        }   
        this.verificarClienteExistente(compra.getCliente());
        if(!compra.getCliente().getCPF().equals("000.000.000-00")){           
            this.verificarUsuarioExiste(compra.getUsuario());
            this.verificarQuantidadeItens(compra.getItensCompra());
            this.verificarStatusCliente(compra.getCliente());
            CompraDAO compraDAO = new CompraDAO();
            compraDAO.inserir(compra);
        }else{
            throw new ClienteInexistenteException("Não é possivel realizar a venda. Cliente Inválido");
        }
    }
    
 
    public void compraAPrazo(Compra compra) throws  ClienteInexistenteException,QuantidadeItensInvalidaException, ClienteDevedorException, SQLException {
        if(compra== null){
            throw new CompraInvalidaException("Essa compra é invalida!");
        }
        this.verificarClienteExistente(compra.getCliente());
        this.verificarUsuarioExiste(compra.getUsuario());
        this.verificarQuantidadeItens(compra.getItensCompra());
        this.verificarStatusCliente(compra.getCliente());
        CompraDAO compraDAO = new CompraDAO();
        compraDAO.inserir(compra);
    }
    
    public void verificarClienteExistente(Cliente cliente) {
        if (cliente == null || cliente.getCPF() == null) {
            throw new ClienteInexistenteException("Não há nenhum cliente cadastrado!");
        }
    }

    public void verificarQuantidadeItens(List<ItensCompra> itens) {
        if (itens == null || itens.size() <= 0) {
            throw new QuantidadeItensInvalidaException("É necessário adicionar algum item na compra!");
        }
    }
    
    public void verificaCliente(Cliente cliente){
        String cpf = "000.000.000-00";
        if(cpf.equals(cliente.getCPF())){
            
        }
    }

    public void verificarStatusCliente(Cliente cliente) {
        if (cliente.isStatus() == false) {
            throw new ClienteDevedorException("Cliente em débito com esse estabelecimente");
        }
    }

    public void verificarUsuarioExiste(Usuario usuario) {
        if (usuario == null) {
            throw new UsuarioInvalidoException("Não usuário cadastrado!");
        }
    }
}
