/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pmriachodoce.SistemaRiachoDoce.entidade;

/**
 *
 * @author Késia Correia
 */
public class Pagamento {
    
    private int pagamentoAVista;
    private int pagamentoAPrazo;
    
    public int getPagamentoAVista() {
        return pagamentoAVista;
    }

    public void setPagamentoAVista(int pagamentoAVista) {
        this.pagamentoAVista = pagamentoAVista;
    }

    public int getPagamentoAPrazo() {
        return pagamentoAPrazo;
    }

    public void setPagamentoAPrazo(int pagamentoAPrazo) {
        this.pagamentoAPrazo = pagamentoAPrazo;
    } 
}
