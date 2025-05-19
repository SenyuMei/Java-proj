/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.poo.decathlon1.dominio;

/**
 *
 * @author dario
 */
public class MarcaEnEventoSaltoAltura extends MarcaEnEventoDeCampo {
    public static double A;
    public static double B;
    public static double C;
    
    
    public MarcaEnEventoSaltoAltura(double marca) {
        super(marca);
        calcularPuntosEvento(PARAM[SALTO_DE_ALTURA][0], 
                         PARAM[SALTO_DE_ALTURA][1], 
                         PARAM[SALTO_DE_ALTURA][2], 
                         this.marca);
    }   
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Salto de Altura: ");
        sb.append("marca = ").append(this.marca).append(" segundos,");
        sb.append("puntos = ").append(this.puntos);
        return sb.toString();
    }
    
}

