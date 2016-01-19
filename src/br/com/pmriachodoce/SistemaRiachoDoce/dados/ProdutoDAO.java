/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pmriachodoce.SistemaRiachoDoce.dados;

import br.com.pmriachodoce.SistemaRiachoDoce.entidade.Produto;
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
public class ProdutoDAO {
     private static String SQL_INSERT = "INSERT INTO PRODUTO (DESCRICAO, ESTOQUE, VALORUNITARIO, QUANTIDADEMINIMA, FORNECEDOR) VALUES(?, ?, ?, ?, ?)";
     private static String SQL_ATUALIZAR_ESTOQUE = "UPDATE PRODUTO SET ESTOQUE = ESTOQUE + ? WHERE CODIGO = ?";
     private static String SQL_RETIRAR_QUANTIDADE_ESTOQUE = "UPDATE PRODUTO SET ESTOQUE = ESTOQUE - ? WHERE CODIGO = ?";
     private static String SQL_ATUALIZAR_PRODUTO = "UPDATE PRODUTO SET DESCRICAO = ?, ESTOQUE = ?, VALORUNITARIO = ?, QUANTIDADEMINIMA = ? WHERE CODIGO = ?";
     private static String SQL_BUSCAR_TODOS = "SELECT * FROM PRODUTO";
     private static String SQL_BUSCAR_POR_CODIGO = "SELECT * FROM PRODUTO WHERE CODIGO = ?";
     private static String SQL_DELETAR_POR_CODIGO = "DELETE FROM PRODUTO WHERE CODIGO = ?";
     private static String SQL_BUSCAR_PRODUTO_POR_DESCRICAO = "SELECT * FROM PRODUTO WHERE DESCRICAO = ?";
     private static String SQL_BUSCAR_PRODUTO_POR_FORNECEDOR = "SELECT * FROM PRODUTO WHERE FORNECEDOR = ?";
    
    public void inserir(Produto produto) throws SQLException {
        PreparedStatement comando = null;
        Connection conexao = null;
        ResultSet resultado = null;
        try {
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
            comando.setString(1, produto.getDescricao());
            comando.setInt(2, produto.getQuantidadeEstoque());
            comando.setDouble(3, produto.getValorUnitario());
            comando.setInt(4, produto.getQuantidadeMinima());
            //resolver a questão do Fornecedor...
            comando.execute();
            resultado = comando.getGeneratedKeys();
            while (resultado.next()) {
                produto.setCodigo(resultado.getInt(1));
            }
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

    public Produto buscarPeloCodigo(int codigo) throws SQLException {
        Produto produto = null;
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        try {
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_BUSCAR_POR_CODIGO);
            comando.setInt(1, codigo);
            resultado = comando.executeQuery();

            while (resultado.next()) {
                produto = new Produto();
                produto.setCodigo(resultado.getInt(1));
                produto.setDescricao(resultado.getString(2));
                produto.setQuantidadeEstoque(resultado.getInt(3));
                produto.setValorUnitario(resultado.getDouble(4));
                produto.setQuantidadeMinima(resultado.getInt(5));
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

        return produto;
    }

    public List<Produto> buscarTodos() throws SQLException {
        Produto produto = null;
        List<Produto> produtos = new ArrayList<>();
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        try {
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_BUSCAR_TODOS);
            resultado = comando.executeQuery();

            while (resultado.next()) {
                produto = new Produto();
                produto.setCodigo(resultado.getInt(1));
                produto.setDescricao(resultado.getString(2));
                produto.setQuantidadeEstoque(resultado.getInt(3));
                produto.setValorUnitario(resultado.getDouble(4));
                produto.setQuantidadeMinima(resultado.getInt(5));
                produtos.add(produto);
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
        return produtos;
    }

    public void atualizarEstoque(Produto produto, int quantidade) throws SQLException {
        PreparedStatement comando = null;
        Connection conexao = null;
        try {
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_ATUALIZAR_ESTOQUE);
            comando.setInt(1, quantidade);
            comando.setInt(2, produto.getCodigo());
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

    public void atualizarDadosProduto(Produto produto) throws SQLException {
        PreparedStatement comando = null;
        Connection conexao = null;
        try {
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_ATUALIZAR_PRODUTO);
            comando.setString(1, produto.getDescricao());
            comando.setInt(2, produto.getQuantidadeEstoque());
            comando.setDouble(3, produto.getValorUnitario());
            comando.setInt(4, produto.getQuantidadeEstoque());
            comando.setInt(5, produto.getCodigo());
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

    public void deletarProduto(Produto produto) throws SQLException {
        PreparedStatement comando = null;
        Connection conexao = null;
        try {
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_DELETAR_POR_CODIGO);
            comando.setInt(1, produto.getCodigo());
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

    public Produto buscarPorDescricao(String descricao) throws SQLException {
        Produto produto = null;
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        try {
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_BUSCAR_PRODUTO_POR_DESCRICAO);
            comando.setString(1, descricao);
            resultado = comando.executeQuery();

            while (resultado.next()) {
                produto = new Produto();
                produto.setCodigo(resultado.getInt(1));
                produto.setDescricao(resultado.getString(2));
                produto.setQuantidadeEstoque(resultado.getInt(3));
                produto.setValorUnitario(resultado.getDouble(4));
                produto.setQuantidadeMinima(resultado.getInt(5));
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

        return produto;
    }
    
    public Produto buscarPorFornecedor(int fornecedor) throws SQLException {
        Produto produto = null;
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        try {
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_BUSCAR_PRODUTO_POR_FORNECEDOR);
            comando.setInt(1, fornecedor);
            resultado = comando.executeQuery();

            while (resultado.next()) {
                produto = new Produto();
                produto.setCodigo(resultado.getInt(1));
                produto.setDescricao(resultado.getString(2));
                produto.setQuantidadeEstoque(resultado.getInt(3));
                produto.setValorUnitario(resultado.getDouble(4));
                produto.setQuantidadeMinima(resultado.getInt(5)); 
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

        return produto;
    }

    public void retirarProdutoEstoque(Connection conexao, Produto produto, int quantidade) throws SQLException {
        PreparedStatement comando = null;
        comando = conexao.prepareStatement(SQL_RETIRAR_QUANTIDADE_ESTOQUE);
        comando.setInt(1, quantidade);
        comando.setInt(2, produto.getCodigo());
        comando.execute();
    }
    
}
