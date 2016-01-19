/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pmriachodoce.SistemaRiachoDoce.dados;

import br.com.pmriachodoce.SistemaRiachoDoce.entidade.Cliente;
import br.com.pmriachodoce.SistemaRiachoDoce.entidade.Compra;
import br.com.pmriachodoce.SistemaRiachoDoce.entidade.ComprasRealizadasClientes;
import br.com.pmriachodoce.SistemaRiachoDoce.entidade.GraficoVendas;
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
public class ComprasRealizadasClientesDAO {
    private static String SQL_INSERT = "INSERT INTO COMPRASREALIZADASCLIENTES(VALORTOTAL, COMPRA) VALUES (?, ?)";
    private static String SQL_BUSCAR_TODOS = "SELECT C.NUMEROCOMPRA, C.STATUS, CLI.NOME, CLI.CPF, C.VALORTOTAL, C.DATACOMPRA FROM COMPRASREALIZADASCLIENTES C JOIN COMPRA CO ON C.COMPRA = CO.NUMEROCOMPRA JOIN CLIENTE CLI ON V.CLIENTE = CLI.CPF";
    private static String SQL_BUSCAR_PELO_CODIGO = "SELECT * FROM COMPRASREALIZADASCLIENTES WHERE NUMEROCOMPRA = ?";
    private static String SQL_BUSCAR_PELA_COMPRA = "SELECT * FROM COMPRASREALIZADASCLIENTES WHERE COMPRA = ?";
    private static String SQL_ATUALIZAR_STATUS_COMPRASREALIZADASCLIENTES = "UPDATE COMPRASREALIZADASCLIENTES SET STATUS = FALSE WHERE NUMEROCOMPRA = ?";
    private static String SQL_BUSCAR_PELO_MES = "SELECT DATA, COUNT(*) AS QUANTIDADE FROM COMPRASREALIZADASCLIENTES WHERE Month(dataCompra) = MONTH(NOW()) GROUP BY DATA";
    
    public void inserir(Connection conexao, Compra compra) throws SQLException {
        PreparedStatement comando = null;
        comando = conexao.prepareStatement(SQL_INSERT);
        comando.setDouble(1, compra.getValorTotal());
        comando.setInt(2, compra.getNumeroCompra());
        comando.execute();

    }
    
    public ComprasRealizadasClientes buscarPorVenda(int numeroCompra) throws SQLException{
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        ComprasRealizadasClientes comprasRealizadasClientes = null;
        try{
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_BUSCAR_PELA_COMPRA);
            comando.setInt(1, numeroCompra);
            resultado = comando.executeQuery();
            
            while(resultado.next()){
                comprasRealizadasClientes = new ComprasRealizadasClientes();
                comprasRealizadasClientes.setNumeroCompra(resultado.getInt("NUIMEROCOMPRA"));
                comprasRealizadasClientes.setDataCompra(resultado.getDate("DATACOMPRA"));
                comprasRealizadasClientes.setStatus(resultado.getBoolean("STATUS"));
                comprasRealizadasClientes.setValorTotal(resultado.getDouble("VALORTOTAL"));
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
        return comprasRealizadasClientes;
    }

    public List<ComprasRealizadasClientes> buscarTodos() throws SQLException {
        ComprasRealizadasClientes comprasRealizadasClientes = null;
        Cliente cliente = null;
        List<ComprasRealizadasClientes> compraRealizadasClientes = new ArrayList<>();
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        try {
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_BUSCAR_TODOS);
            resultado = comando.executeQuery();

            while (resultado.next()) {
                
                comprasRealizadasClientes = new ComprasRealizadasClientes();
                comprasRealizadasClientes.setNumeroCompra(resultado.getInt(1));
                comprasRealizadasClientes.setStatus(resultado.getBoolean(2));
                comprasRealizadasClientes.setValorTotal(resultado.getDouble(5));
                comprasRealizadasClientes.setDataCompra(resultado.getDate(6));

                cliente = new Cliente();
                cliente.setCPF(resultado.getString(4));
                cliente.setNome(resultado.getString(3));

                Compra cp = new Compra();
                cp.setCliente(cliente);
                comprasRealizadasClientes.setCompra(cp);
                compraRealizadasClientes.add(comprasRealizadasClientes);
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
        return compraRealizadasClientes;
    }

    public ComprasRealizadasClientes buscarByCodigo(Connection conexao, int numeroCompra) throws SQLException {
        ComprasRealizadasClientes comprasRealizadasClientes = null;
        Compra compra = new Compra();
        Cliente cliente = null;
        Usuario usuario = null;
        
        PreparedStatement comando = null;
        ResultSet resultado = null;
        
        conexao = BancoDadosUtil.getConnection();
        comando = conexao.prepareStatement(SQL_BUSCAR_PELO_CODIGO);
        comando.setInt(1, numeroCompra);
        resultado = comando.executeQuery();

        while (resultado.next()) {
           comprasRealizadasClientes = new ComprasRealizadasClientes();
           comprasRealizadasClientes.setNumeroCompra(resultado.getInt(1));
           comprasRealizadasClientes.setStatus(resultado.getBoolean(2));
           comprasRealizadasClientes.setValorTotal(resultado.getDouble(5));
           comprasRealizadasClientes.setDataCompra(resultado.getDate(8));
           
           cliente = new Cliente();
           cliente.setCPF(resultado.getString("CLIENTECPF"));
           cliente.setNome(resultado.getString("CLIENTENOME"));
           
           usuario = new Usuario();
           usuario.setCPF(resultado.getString("USUARIOCPF"));
           usuario.setNome(resultado.getString("USUARIONOME"));
           
           compra.setCliente(cliente);
           compra.setUsuario(usuario);
           
           comprasRealizadasClientes.setCompra(compra);
        }
        return comprasRealizadasClientes;
    }
    
    public void atualizarStatusContaPaga(int numeroCompra) throws SQLException{
        Connection conexao = null;
        PreparedStatement comando = null;
        
        try {
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_ATUALIZAR_STATUS_COMPRASREALIZADASCLIENTES);
            comando.setInt(1, numeroCompra);
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
    
    public List<GraficoVendas> buscarPorMesGrafico() throws SQLException {
        List<GraficoVendas> vendas = new ArrayList<>();
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        try {
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_BUSCAR_PELO_MES);
            resultado = comando.executeQuery();

            while (resultado.next()) {
                GraficoVendas graficoVendas = new GraficoVendas();
                graficoVendas.setData(resultado.getDate(1));
                graficoVendas.setQuantidade(resultado.getInt(2));
                vendas.add(graficoVendas);
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
        return vendas;
    }
}
