/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.poo.decathlon1.dominio;

/**
 *
 * @author dario
 */
public class NumeroDeAtletasException extends Exception {
    public NumeroDeAtletasException() {
        super("ERROR: Número de atletas erróneo.");
    }
    public NumeroDeAtletasException(String msg) {
        super(msg);
    }
}
