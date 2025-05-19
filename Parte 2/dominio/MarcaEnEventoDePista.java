/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.poo.decathlon1.dominio;

/**
 *
 * @author dario
 */
public abstract class MarcaEnEventoDePista extends MarcaEnEvento{

    public MarcaEnEventoDePista(double marca) {
        super(marca);
 
    }

    @Override
    public void calcularPuntosEvento(double A, double B, double C, double marca) {
       this.puntos = (int) Math.floor(A * Math.pow(marca - B, C));//revisar formula
    }

}
