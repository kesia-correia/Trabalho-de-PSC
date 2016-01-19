/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pmriachodoce.SistemaRiachoDoce.excecoes;

/**
 *
 * @author Késia Correia
 */
public class CampoObrigatorioException extends Exception{
    public CampoObrigatorioException(){
        super("Favor informar o(s) campo(s) obrigatório(s).");
    }
    
}
