/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.poo.decathlon1.dominio;

/**
 *
 * @author dario
 */
public class MarcaEnEventoLanzamientoPeso extends MarcaEnEventoDeCampo {
    public static double A;
    public static double B;
    public static double C;    

    
    public MarcaEnEventoLanzamientoPeso(double marca) {
        super(marca);
        calcularPuntosEvento(PARAM[LAZAMIENTO_DE_PESO][0], 
                         PARAM[LAZAMIENTO_DE_PESO][1], 
                         PARAM[LAZAMIENTO_DE_PESO][2], 
                         this.marca);
    }   
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Lanzamiento de Peso: ");
        sb.append("marca = ").append(this.marca).append(" segundos,");
        sb.append("puntos = ").append(this.puntos);
        return sb.toString();
    }
    
}
