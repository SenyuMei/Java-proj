/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.poo.decathlon1.dominio;

/**
 *
 * @author dario
 */
public class MarcaEnEvento400m extends MarcaEnEventoDePista {
    public static double A;
    public static double B;
    public static double C;
    

    public MarcaEnEvento400m(double marca) {
        super(marca);
        calcularPuntosEvento(PARAM[CUATROCIENTOS_METROS][0], 
                         PARAM[CUATROCIENTOS_METROS][1], 
                         PARAM[CUATROCIENTOS_METROS][2], 
                         this.marca);
    }   
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("400 metros lisos: ");
        sb.append("marca = ").append(this.marca).append(" segundos,");
        sb.append("puntos = ").append(this.puntos);
        return sb.toString();
    }
}
