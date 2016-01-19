/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pmriachodoce.SistemaRiachoDoce.negocio;

import br.com.pmriachodoce.SistemaRiachoDoce.dados.ProdutoDAO;
import br.com.pmriachodoce.SistemaRiachoDoce.entidade.Produto;
import br.com.pmriachodoce.SistemaRiachoDoce.excecoes.DescricaoDuplicadaProdutoException;
import br.com.pmriachodoce.SistemaRiachoDoce.excecoes.QuantidadeProdutoInvalidaException;
import br.com.pmriachodoce.SistemaRiachoDoce.excecoes.ValorProdutoInvalidoException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Késia Correia
 */
public class ProdutoBO {
    
    public void criar(Produto produto) throws DescricaoDuplicadaProdutoException, QuantidadeProdutoInvalidaException, ValorProdutoInvalidoException, SQLException {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        Produto produtoExistente = produtoDAO.buscarPorDescricao(produto.getDescricao());
        
        if (produtoExistente == null) {
            verificarQuantidade(produto);
            verificarValorProduto(produto);
            produtoDAO.inserir(produto);
        }else{
            throw new DescricaoDuplicadaProdutoException("ATENÇÃO:Já existe um produto cadastrado com essa descrição! ");
        }

    }

    public void verificarQuantidade(Produto produto) {
        if (produto.getQuantidadeEstoque() <= 0) {
            throw new QuantidadeProdutoInvalidaException("O produto a ser cadastrado deve ter no minimo um item no estoque");
        } else if (produto.getQuantidadeMinima() > produto.getQuantidadeEstoque()) {
            throw new QuantidadeProdutoInvalidaException("A quantidade mínima não pode ser maior do que a quantidade de itens que se tem armazenado no estoque");
        }
    }
    
    public void verificarQuantidade(int quantidade) {
        if ( quantidade <= 0) {
            throw new QuantidadeProdutoInvalidaException("Para atualizar o estoque deve haver pelo menos um item");
        } 
    }

    public void verificarValorProduto(Produto produto) {
        if (produto.getValorUnitario() <= 0) {
            throw new ValorProdutoInvalidoException("O valor do produto deve ser maior que zero");
        }
    }

    public void atualizarEstoque(Produto produto, int quantidade) throws QuantidadeProdutoInvalidaException, SQLException {
        verificarQuantidade(quantidade);
        ProdutoDAO produtoDAO = new ProdutoDAO();
        produtoDAO.atualizarEstoque(produto, quantidade);
    }

    public void excluirProduto(Produto produto) throws SQLException {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        produtoDAO.deletarProduto(produto);
    }

    public void atualizarDadosProduto(Produto produto) throws QuantidadeProdutoInvalidaException, ValorProdutoInvalidoException, SQLException {
        verificarQuantidade(produto);
        verificarValorProduto(produto);
        ProdutoDAO produtoDAO = new ProdutoDAO();
        produtoDAO.atualizarDadosProduto(produto);
    }
    
    public List<Produto> buscarTodos() throws SQLException{
        ProdutoDAO produtoDAO = new ProdutoDAO();
        return produtoDAO.buscarTodos();
    }
    
    public Produto buscarByCodigo(int codigo) throws SQLException{
        ProdutoDAO produtoDAO = new ProdutoDAO();
        return produtoDAO.buscarPeloCodigo(codigo);
    }
}
