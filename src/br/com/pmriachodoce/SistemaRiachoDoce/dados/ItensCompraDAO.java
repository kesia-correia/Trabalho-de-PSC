/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pmriachodoce.SistemaRiachoDoce.dados;

import br.com.pmriachodoce.SistemaRiachoDoce.entidade.ItensCompra;
import br.com.pmriachodoce.SistemaRiachoDoce.entidade.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Késia Correia
 */
public class ItensCompraDAO {
    
    private static String SQL_INSERT = "INSERT INTO ITENSCOMPRA (PRODUTOCODIGO, DESCRICAO, VALORUNITARIO, VALORTOTAL, COMPRA) VALUES(?, ?, ?, ?, ?)";

    public void criar(Connection conexao, int compra, ItensCompra item) throws SQLException {
        PreparedStatement comando = null;
        comando = conexao.prepareStatement(SQL_INSERT);
        comando.setInt(1, item.getProduto().getCodigo());
        comando.setString(2, item.getProduto().getDescricao());
        comando.setDouble(3, item.getValorUnitario());
        comando.setDouble(4, item.getValorTotal());
        comando.setInt(5, compra);
        comando.execute();
        retirarProdutoEstoque(conexao, item.getProduto(), item.getQuantidade());
    }
    
    public void retirarProdutoEstoque(Connection conexao, Produto produto, int quantidade) throws SQLException{
        ProdutoDAO produtoDAO = new ProdutoDAO();
        produtoDAO.retirarProdutoEstoque(conexao, produto, quantidade);
    }
}
