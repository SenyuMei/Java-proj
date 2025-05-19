/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.poo.decathlon1.dominio;

/**
 *
 * @author dario
 */
public class MarcaEnEventoSaltoLongitud extends MarcaEnEventoDeCampo {
    public static double A;
    public static double B;
    public static double C;
    
    public MarcaEnEventoSaltoLongitud(double marca) {
        super(marca);
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Salto de Longitud:").append("marca = ").append("a").append("centimetros").append(getPuntos());
        return sb.toString();
    }
    
}
