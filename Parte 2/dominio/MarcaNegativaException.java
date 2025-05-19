/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.poo.decathlon1.dominio;

/**
 *
 * @author dario
 */
public class MarcaNegativaException extends Exception{
    public MarcaNegativaException() {
        super("ERROR: La marca es negativa");
    }
    public MarcaNegativaException(String msg){
        super(msg);
    }
    
}
