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
public class UsuarioCadastradoException extends RuntimeException{
    
    public UsuarioCadastradoException (String mensagem){
        super(mensagem);
    }
    
}
