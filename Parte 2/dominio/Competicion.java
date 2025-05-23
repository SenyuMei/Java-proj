package edu.upc.etsetb.poo.decathlon1.dominio;

/**
 * Clase para gestionar la competición.
 */
public class Competicion {


    private final String nombre;
    private final String fecha;
    private int numInscritos = 0;
    private final String lugar;
    
    public Competicion(String nombre, String fecha, String lugar) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.lugar = lugar;
    }

    public int getNumInscritos() {
        return this.numInscritos;
    }

    public int obtenerSiguienteNumInscripcion() {
      return ++this.numInscritos;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nombre de la competicion: ").append(this.nombre).append("\n");
        sb.append("Fecha de celebracion: ").append(this.fecha).append("\n");
        sb.append("Lugar: ").append(this.lugar).append("\n");
        sb.append("Numero de atletas inscritos: ").append(this.numInscritos).append("\n");
        return sb.toString();
    }

}
