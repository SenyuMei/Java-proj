/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.poo.decathlon1.dominio;

/**
 *
 * @author dario
 */
public class NumInscripcionException extends Exception {
    public NumInscripcionException() {
        super("ERROR: El número de inscripción es erróneo.");
    }
    public NumInscripcionException(String msg) {
        super(msg);
    }
}
