/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pmriachodoce.SistemaRiachoDoce.entidade;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Késia Correia
 */
public class Compra {
    
    private double valorTotal;
    private Date dataCompra;
    private int numeroCompra;
    private Cliente cliente;
    private Usuario usuario;
    private List<ItensCompra> itensCompra = new ArrayList<>();
    
    public Compra(){
        this.dataCompra = new Date(System.currentTimeMillis());
        this.valorTotal = 0;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Date getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(Date data) {
        this.dataCompra = dataCompra;
    }

    public int getNumeroCompra() {
        return numeroCompra;
    }

    public void setNumeroCompra(int numeroCompra) {
        this.numeroCompra = numeroCompra;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<ItensCompra> getItensCompra() {
        return itensCompra;
    }

    public void setItensCompra(List<ItensCompra> itensCompra) {
        this.itensCompra = itensCompra;
    }
    
    public void adicionarItemCompra(ItensCompra itemCompra) {
        this.itensCompra.add(itemCompra);
        this.valorTotal = this.valorTotal + itemCompra.getValorTotal();
    }
    
    public void removeItemCompra(int index){
        ItensCompra item = this.itensCompra.get(index);
        this.valorTotal = this.valorTotal = item.getValorTotal();
        this.itensCompra.remove(index);
    }
    
    public String getDataFormatada(){
        SimpleDateFormat dt1 = new SimpleDateFormat("dd/MM/yyyy");
        return dt1.format(this.dataCompra);
    }
    
    public String getDataFormatadaBanco(){
        SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
        return dt1.format(this.dataCompra);
    }
    
}
