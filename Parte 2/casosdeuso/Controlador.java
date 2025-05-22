package edu.upc.etsetb.poo.decathlon1.casosdeuso;

import edu.upc.etsetb.poo.decathlon1.dominio.Atleta;
import edu.upc.etsetb.poo.decathlon1.dominio.Clasificacion;
import edu.upc.etsetb.poo.decathlon1.dominio.Competicion;
import edu.upc.etsetb.poo.decathlon1.dominio.MarcaEnEvento;
import edu.upc.etsetb.poo.decathlon1.dominio.MarcaNegativaException;
import edu.upc.etsetb.poo.decathlon1.dominio.NoAtletasInscritosException;
import edu.upc.etsetb.poo.decathlon1.dominio.NumInscripcionException;
import edu.upc.etsetb.poo.decathlon1.dominio.NumeroDeAtletasException;
import edu.upc.etsetb.poo.decathlon1.dominio.TipoEventoException;
import edu.upc.etsetb.poo.decathlon1.iu.InterficieUsuario;

import java.util.HashMap;
import java.util.Map;

public class Controlador {

    private final Map<Integer, Atleta> atletas;
    private final Competicion competicion;
    private Clasificacion clasificacion;
    private final InterficieUsuario iu;

    public Controlador(String nombre, String fecha, String lugar,
                       InterficieUsuario iu, boolean inicializa)
            throws NoAtletasInscritosException, NumInscripcionException,
                   TipoEventoException, MarcaNegativaException {
        this.iu = iu;
        this.atletas = new HashMap<>();
        this.competicion = new Competicion(nombre, fecha, lugar);
        if (inicializa) {
            inicializaConAtletasYMarcasIniciales();
        }
    }

    public void inscribirAtleta(String nombre, String nacionalidad) {
        int numInscripcion = competicion.obtenerSiguienteNumInscripcion();
        Atleta atleta = new Atleta(nombre, nacionalidad, numInscripcion);
        atletas.put(numInscripcion, atleta);
    }

    public String getInfoCompeticion() {
        return competicion.toString();
    }
    
    public int getNumInscritosEnCompeticion() {
        return competicion.getNumInscritos();
    }
    
    public String getInfoAtleta(int numInscripcion) throws NumInscripcionException {
        Atleta atleta = atletas.get(numInscripcion);
        if(atleta == null) {
            throw new NumInscripcionException("No atletas inscritos");
        }
        return atleta.toString();
    }

    public void anyadirMarcaEnEventoDeUnAtleta(int numInscripcion, int evento, double marca)
            throws NoAtletasInscritosException, NumInscripcionException,
                   TipoEventoException, MarcaNegativaException {
        if (atletas.isEmpty()) {
            throw new NoAtletasInscritosException();
        }

        Atleta atleta = atletas.get(numInscripcion);
        if (atleta == null) {
            throw new NumInscripcionException("Numero de atletas no inscritos.");
        }

        if (evento < 0 || evento >= MarcaEnEvento.NUM_EVENTOS) {
            throw new TipoEventoException("Numero de evnto erroneo.");
        }

        if (marca < 0) {
            throw new MarcaNegativaException("Marca negativa.");
        }

        atleta.anyadirMarcaEnEvento(evento, marca);
    }

    public String getClasificacion(int numAtletas)
            throws NoAtletasInscritosException, NumeroDeAtletasException {
        if (atletas.isEmpty()) {
            throw new NoAtletasInscritosException("No ateltas inscritos.");
        }

        if (numAtletas < 1 || numAtletas > atletas.size()) {
            throw new NumeroDeAtletasException("Numero de atletas erroneo");
        }

        clasificacion = new Clasificacion(numAtletas);
        for (Atleta atleta : atletas.values()) {
            clasificacion.anyadirAClasificacion(atleta);
        }
        return clasificacion.toString();
    }

    public void inicializaConAtletasYMarcasIniciales() throws NoAtletasInscritosException, NumInscripcionException, TipoEventoException, MarcaNegativaException {
        inscribirAtleta("Kevin Mayer", "FRA");      // 800 pts por prueba
        inscribirAtleta("Larbi Bourrada", "ALG");   // 1000 pts por prueba
        inscribirAtleta("Dimitriy Karpov", "KAZ");  // 900 pts por prueba
        inscribirAtleta("Ashton Eaton", "USA");     // 700 pts por prueba
        inscribirAtleta("Ashley Moloney", "AUS");   // Lesionado

        int i = 1;
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
        // Ashley Moloney no compite
    }
}
