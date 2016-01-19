/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pmriachodoce.SistemaRiachoDoce.negocio;

import br.com.pmriachodoce.SistemaRiachoDoce.dados.ComprasRealizadasClientesDAO;
import br.com.pmriachodoce.SistemaRiachoDoce.entidade.ComprasRealizadasClientes;
import br.com.pmriachodoce.SistemaRiachoDoce.entidade.GraficoVendas;
import br.com.pmriachodoce.SistemaRiachoDoce.excecoes.CompraInvalidaException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Késia Correia
 */
public class ComprasRealizadasClientesBO {
    public List<ComprasRealizadasClientes> buscarTodos() throws SQLException{
        ComprasRealizadasClientesDAO compraRealizadasClientesDAO = new ComprasRealizadasClientesDAO();
        return compraRealizadasClientesDAO.buscarTodos();
    }
    
    public List<GraficoVendas> buscarPorMes() throws SQLException{
        ComprasRealizadasClientesDAO comprasRealizadasClientesDAO = new ComprasRealizadasClientesDAO();
        List<GraficoVendas> vendas = null;    
        vendas = comprasRealizadasClientesDAO.buscarPorMesGrafico();
        if(vendas == null || vendas.size() <= 0){
            throw new CompraInvalidaException("Não há cadastrado de compras para esse mês!(Nenhuma compra efetuada)!");
        }
        return vendas;
    }
}
