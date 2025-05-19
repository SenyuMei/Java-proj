/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.poo.decathlon1.dominio;

/**
 *
 * @author dario
 */
public class NoAtletasInscritosException extends Exception{
    public NoAtletasInscritosException() {
        super("ERROR: Aún no hay ningún atleta inscrito.");
    }
    public NoAtletasInscritosException (String msg){
        super(msg);
    }   
}
