package edu.upc.etsetb.poo.decathlon1.casosdeuso;

import edu.upc.etsetb.poo.decathlon1.dominio.Atleta;
import edu.upc.etsetb.poo.decathlon1.dominio.Clasificacion;
import edu.upc.etsetb.poo.decathlon1.dominio.Competicion;
import edu.upc.etsetb.poo.decathlon1.dominio.MarcaEnEvento;
import edu.upc.etsetb.poo.decathlon1.iu.InterficieUsuario;

import java.util.HashMap;
import java.util.Map;

/**
 * El controlador del programa.
 */
public class Controlador {

    public static final int RESULTADO_OK = 0;
    public static final int NO_ATLETAS_INSCRITOS = 1;
    public static final int NUM_INSCRIPCION_ERRONEO = 2;
    public static final int TIPO_DE_EVENTO_ERRONEO = 3;

    private final Map<Integer, Atleta> atletas;
    private final Competicion competicion;
    private Clasificacion clasificacion;
    private final InterficieUsuario iu;

    /**
     * Constructor de la clase Controlador.
     * 
     * @param nombre Nombre de la competición.
     * @param fecha Fecha de la competición.
     * @param lugar Lugar donde se celebra.
     * @param iu Interficie de usuario.
     * @param inicializa Si es verdadero, inicializa con atletas y marcas.
     */
    public Controlador(String nombre, String fecha, String lugar, InterficieUsuario iu, boolean inicializa) {
        this.competicion = new Competicion(nombre, fecha, lugar);
        this.iu = iu;
        this.atletas = new HashMap<>();
        if (inicializa) {
            inicializaConAtletasYMarcasIniciales();
        }
    }

    public void inscribirAtleta(String nombre, String nacionalidad) {
        int numInscripcion = this.competicion.obtenerSiguienteNumInscripcion();
        Atleta atleta = new Atleta(nombre, nacionalidad, numInscripcion);
        this.atletas.put(numInscripcion, atleta);
    }

    public String getInfoCompeticion() {
        return this.competicion.toString();
    }

    public int getNumInscritosEnCompeticion() {
        return this.competicion.getNumInscritos();
    }

    public String getInfoAtleta(int numInscripcion) {
        if (this.atletas.containsKey(numInscripcion)) {
            return this.atletas.get(numInscripcion).toString();
        } else {
            return InterficieUsuario.NUM_INSCRIPCION_ERRONEO_STR;
        }
    }
    public int anyadirMarcaEnEventoDeUnAtleta(int numInscripcion, int evento, double marca) {
        
        if (this.atletas.isEmpty()) {
            return NO_ATLETAS_INSCRITOS;
        } else if (numInscripcion <= 0) {
            return NUM_INSCRIPCION_ERRONEO;
        } else if (evento < 0 || evento >= MarcaEnEvento.NUM_EVENTOS) {
            return TIPO_DE_EVENTO_ERRONEO;
        } else {
            Atleta atleta = this.atletas.get(numInscripcion);
            atleta.anyadirMarcaEnEvento(evento, marca);
            return RESULTADO_OK;
        }
    }

    public String getClasificacion(int numAtletas) {
        if (this.atletas.isEmpty()) {
            return InterficieUsuario.NO_ATLETAS_INSCRITOS_STR;
        } else if (numAtletas < 1 || numAtletas > this.competicion.getNumInscritos()) {
            return InterficieUsuario.NUM_ATLETAS_ERRONEO_STR;
        } else {
            this.clasificacion = new Clasificacion(numAtletas);
            for (Atleta atleta : this.atletas.values()) {
                this.clasificacion.anyadirAClasificacion(atleta);
            }
            return this.clasificacion.toString();
        }
    }

    public void inicializaConAtletasYMarcasIniciales() {
        inscribirAtleta("Kevin Mayer", "FRA");
        inscribirAtleta("Larbi Bourrada", "ALG");
        inscribirAtleta("Dimitriy Karpov", "KAZ");
        inscribirAtleta("Ashton Eaton", "USA");
        inscribirAtleta("Ashley Moloney", "AUS");

        int i = 0;
        // 800 pts
        anyadirMarcaEnEventoDeUnAtleta(i, 0, 11.278);
        anyadirMarcaEnEventoDeUnAtleta(i, 1, 694);
        anyadirMarcaEnEventoDeUnAtleta(i, 2, 15.16);
        anyadirMarcaEnEventoDeUnAtleta(i, 3, 199);
        anyadirMarcaEnEventoDeUnAtleta(i, 4, 50.32);
        i++;
        // 1000 pts
        anyadirMarcaEnEventoDeUnAtleta(i, 0, 10.395);
        anyadirMarcaEnEventoDeUnAtleta(i, 1, 776);
        anyadirMarcaEnEventoDeUnAtleta(i, 2, 18.40);
        anyadirMarcaEnEventoDeUnAtleta(i, 3, 221);
        anyadirMarcaEnEventoDeUnAtleta(i, 4, 46.17);
        i++;
        // 900 pts
        anyadirMarcaEnEventoDeUnAtleta(i, 0, 10.827);
        anyadirMarcaEnEventoDeUnAtleta(i, 1, 736);
        anyadirMarcaEnEventoDeUnAtleta(i, 2, 16.79);
        anyadirMarcaEnEventoDeUnAtleta(i, 3, 210);
        anyadirMarcaEnEventoDeUnAtleta(i, 4, 48.19);
        i++;
        // 700 pts
        anyadirMarcaEnEventoDeUnAtleta(i, 0, 11.756);
        anyadirMarcaEnEventoDeUnAtleta(i, 1, 651);
        anyadirMarcaEnEventoDeUnAtleta(i, 2, 13.53);
        anyadirMarcaEnEventoDeUnAtleta(i, 3, 188);
        anyadirMarcaEnEventoDeUnAtleta(i, 4, 52.58);
        i++;
        // 600 pts (para el quinto atleta)
        anyadirMarcaEnEventoDeUnAtleta(i, 0, 12.267);
        anyadirMarcaEnEventoDeUnAtleta(i, 1, 607);
        anyadirMarcaEnEventoDeUnAtleta(i, 2, 11.90);
        anyadirMarcaEnEventoDeUnAtleta(i, 3, 176);
        anyadirMarcaEnEventoDeUnAtleta(i, 4, 54.97);
    }
}
