/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.poo.decathlon1.dominio;

/**
 *
 * @author dario
 */
public class TipoEventoException extends Exception {
    public TipoEventoException() {
        super("ERROR: Número de tipo de evento erróneo.");
    }
    public TipoEventoException(String msg) {
        super(msg);
    }
}
