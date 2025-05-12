package edu.upc.etsetb.poo.decathlon1.dominio;

/**
 * Clase para gestionar la información de un atleta
 * 
 */
public class Atleta {

    private final int numInscripcion;
    private final String nombre;
    private final String nacionalidad;
    private int puntos;
    private final MarcaEnEvento[] marcas;

    /**
     * Método constructor de la clase

     */
    public Atleta(String nombre, String nacionalidad, int numInscripcion) {
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.numInscripcion = numInscripcion;
        this.marcas = new MarcaEnEvento[MarcaEnEvento.NUM_EVENTOS];
    }

    /**
     * Método getter. Devuelve el número de inscripción.
     *
     *
     */
    public int getNumInscripcion() {
      return this.numInscripcion;
    }

    /**
     * Método getter. Devuelve el nombre del atleta.
     *
     *
     */
    public String getNombre() {
      return this.nombre;
    }

    /**
     * Método getter. Devuelve la nacionalidad del atleta.
     *
     */
    public String getNacionalidad() {
       return this.nacionalidad;
       
       }

    /**
     * Método getter. Devuelve la suma de los puntos obtenidos por el atleta en
     * las diferentes pruebas en las que ha participado.
     *
     */
    public int getPuntos() {
       return this.puntos;
    }

    /**
     * Crea una nueva MarcaEnEvento, la añade en la posición evento del vector
     * marcas, calcula los puntos correspondientes a ese evento para este atleta
     * y recalcula los puntos totales del atleta.
     *
     * @param evento el evento en el que el atleta ha conseguido la marca
     * @param marca la marca conseguida por el atleta
     */
    //revisar
    public void anyadirMarcaEnEvento(int evento, double marca) {
        MarcaEnEvento nuevaMarca = new MarcaEnEvento(evento, marca);
        nuevaMarca.calcularPuntosEvento(); // 
        marcas[evento] = nuevaMarca;
        calcularPuntos(); // recalcula puntos totales
    }


    /**
     * Recalcula los puntos totales obtendos por el atleta hasta el momento.
     */
    public void calcularPuntos() {
        this.puntos = 0;
        for (MarcaEnEvento marca : this.marcas) {
            if (marca != null) {
                this.puntos += marca.getPuntos();
            }
        }
    }

    /**
     * Método toString() de la clase.
     *
     * String con la información del atleta. A continuación se muestra
     * un ejemplo de su contenido:<br><br>
     * Número de inscripción: 1<br>
     * Nombre: Pepe Pérez<br>
     * Nacionalidad:ES<br>
     * 100 metros lisos:     marca=11.278 segundos, puntos=800<br>
     * Salto de longitud:    marca=694.0 centimetros, puntos=799<br>
     * Lanzamiento de peso:  marca=15.16 metros, puntos=800<br>
     * Salto de altura:      marca=199.0 centimetros, puntos=794<br>
     * 400 metros lisos:     marca=50.32 segundos, puntos=800<br>
     * <br>
     * Puntos totales: 3993<br>
     * 
        */
   @Override
   public String toString() {
       StringBuilder sb = new StringBuilder();
       sb.append("Numero de inscripcion: ").append(this.numInscripcion).append("\n");
       sb.append("Nombre: ").append(this.nombre).append("\n");
       sb.append("Nacionalidad: ").append(this.nacionalidad).append("\n");

       for (MarcaEnEvento marca : marcas) {
           if (marca != null) {
               sb.append(marca.toString()).append("\n");
           }
       }

       sb.append("\nPuntos totales: ").append(this.puntos).append("\n");

       return sb.toString();
   }

}

