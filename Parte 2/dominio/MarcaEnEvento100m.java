/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.poo.decathlon1.dominio;

/**
 *
 * @author dario
 */
public class MarcaEnEvento100m extends MarcaEnEventoDePista{
    public static double A;
    public static double B;
    public static double C;
    

    public MarcaEnEvento100m(double marca) {
        super(marca);
        calcularPuntosEvento(PARAM[CIEN_METROS][0], 
                         PARAM[CIEN_METROS][1], 
                         PARAM[CIEN_METROS][2], 
                         this.marca);
    }   
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("100 metros lisos: ");
        sb.append("marca = ").append(this.marca).append(" segundos,");
        sb.append("puntos = ").append(this.puntos);
        return sb.toString();
    }
}
