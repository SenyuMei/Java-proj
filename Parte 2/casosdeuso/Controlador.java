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
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * El controlador del programa.
 */
public final class Controlador {

    /**
     * Código entero indicativo de que todo ha ido bien
     */
    public static final int RESULTADO_OK = 0;
    /**
     * Código de error indicativo de que no hay atletas inscritos
     */
    public static final int NO_ATLETAS_INSCRITOS = 1;
    /**
     * Código de error indicativo de que el número de inscripción es erróneo
     */
    public static final int NUM_INSCRIPCION_ERRONEO = 2;
    /**
     * Código de error indicativo de que el número indicativo del evento es
     * erróneo
     */
    public static final int TIPO_DE_EVENTO_ERRONEO = 3;

    /**
     * El contenedor de atletas: un mapa que mapea un atleta (valor) con su
     * número de inscripción (clave)
     */
    private final Map<Integer, Atleta> atletas;
    /**
     * La competición
     */
    private final Competicion competicion;
    /**
     * La clasificación
     */
    private Clasificacion clasificacion;
    /**
     * La interficie de usuario
     */
    private InterficieUsuario iu;

    /**
     * Método constructor de la clase. Se le pasa la información correspondiente
     * a la competición y la crea.Dependiendo del valor del argumento inicializa
     * este método puede invocar Controlador::inicializaConAtletasYMarcas
     * Iniciales() para inscribir atletas y añadir marcas y así no tener que
     * hacerlo a mano cada vez que se ejecute el programa.
     *
     * @param nombre Nombre de la competición.
     * @param fecha Fecha de la competición.
     * @param lugar Lugar donde se celebra.
     * @param iu la interficie de usuario.
     * @param inicializa indica si se quiere que el controlador invoque al
     * método Controlador::inicializaConAtletasYMarcasIniciales() para inscribir
     * atletas y añadir sus marcas, o no (en este último caso no se inscribirían
     * atletas ni se añadiría ninguna marca)
     * @throws edu.upc.etsetb.poo.decathlon1.dominio.NoAtletasInscritosException
     * @throws edu.upc.etsetb.poo.decathlon1.dominio.NumInscripcionException
     * @throws edu.upc.etsetb.poo.decathlon1.dominio.TipoEventoException
     * @throws edu.upc.etsetb.poo.decathlon1.dominio.MarcaNegativaException
     */
    public Controlador(String nombre, String fecha,
                       String lugar, InterficieUsuario iu, boolean inicializa) throws NoAtletasInscritosException, NumInscripcionException, TipoEventoException, MarcaNegativaException {
        this.iu = iu;
        this.atletas = new HashMap<>();
        this.competicion = new Competicion(nombre, fecha, lugar);

        if (inicializa) {
            inicializaConAtletasYMarcasIniciales();
        }
    }

    public void inscribirAtleta(String nombre, String nacionalidad) {
        try {
            int numInscripcion = competicion.obtenerSiguienteNumInscripcion();
            if (numInscripcion == 0) {
                throw new NoAtletasInscritosException("No hay atletas inscritos.");
            }
            Atleta atleta = new Atleta(nombre, nacionalidad, numInscripcion);
            atletas.put(numInscripcion, atleta);

        } catch (NoAtletasInscritosException e) {
            System.out.println(e.getMessage());
        }
    }


    public String getInfoCompeticion() {
        try {
            if (!atletas.isEmpty()) {
                return competicion.toString();
            } 
        
        } catch (NoAtletasInscritosException e) {
            return e.getMessage();
        }
        return null;
    }

    public int getNumInscritosEnCompeticion() {
        return competicion.getNumInscritos();
    }

    public String getInfoAtleta(int numInscripcion) {
        try {
            if (numInscripcion < 0) {
                throw new NumInscripcionException("Número de inscripción inválido: no puede ser negativo.");
            }
            if (numInscripcion == atletas.size()) {
                throw new NumInscripcionException("Número de inscripción inválido: fuera de rango.");
            }
        Atleta atleta = atletas.get(numInscripcion);
            return atleta.toString();
        } catch (NumInscripcionException e) {
            return e.getMessage();
        }
    }

    public void anyadirMarcaEnEventoDeUnAtleta (int numInscripcion, int evento, double marca) throws NoAtletasInscritosException, NumInscripcionException, TipoEventoException, MarcaNegativaException {
        if (atletas.isEmpty()) {
            throw new NoAtletasInscritosException("ERROR: Aún no hay ningún atleta inscrito.");
        }

        Atleta atleta = atletas.get(numInscripcion);
        if (atleta == null) {
            throw new NumInscripcionException("ERROR: El número de inscripción es erróneo.");
        }

        if (evento < 0 || evento >= MarcaEnEvento.NUM_EVENTOS) {
            throw new TipoEventoException("ERROR: Evento Incorreto!");
        }
        if ( marca < 0) {
            throw new MarcaNegativaException("ERROR: La marca es negativa");
        }

        atleta.anyadirMarcaEnEvento(evento, marca);
        System.out.println("Marca añadida!");
    }

    public String getClasificacion(int numAtletas) throws NoAtletasInscritosException, NumeroDeAtletasException{
        if (atletas.isEmpty()) {
            throw new NoAtletasInscritosException("No hay atletas inscritos.");
        }

        if (numAtletas < 1 || numAtletas > atletas.size()) {
            throw new NumeroDeAtletasException("ERROR: Número de atletas erróneo.");
        }
        
        if (numAtletas == atletas.size()) {
            throw new NoAtletasInscritosException("No hay atletas inscritos.");
        }

        clasificacion = new Clasificacion(numAtletas);

        // Añadimos directamente todos los atletas
        for (Atleta atleta : atletas.values()) {
            clasificacion.anyadirAClasificacion(atleta); // ya ordena y limita
        }

        return clasificacion.toString();
    }


    /**
     * Este método se os da hecho y no tenéis que modificarlo: inicializa el
     * Controlador con 5 atletas y todas sus marcas; el corrector automático
     * utiliza este método para realizar sus correcciones; NO LO TOQUÉIS.
     * Echadle un vistazo para ver cómo se inscriben atletas y se añaden sus
     * marcas A continuación se muestra un trozo del código que contiene este
     * método:<br>
     * inscribirAtleta("Kevin Mayer", "FRA");<br>
     * anyadirMarcaEnEventoDeUnAtleta(1, 0, 11.278);<br>
     * anyadirMarcaEnEventoDeUnAtleta(i, 1, 694);<br>
     * anyadirMarcaEnEventoDeUnAtleta(i, 2, 15.16);<br>
     * anyadirMarcaEnEventoDeUnAtleta(i, 3, 199);<br>
     * anyadirMarcaEnEventoDeUnAtleta(i, 4, 50.32);<br>
     *
     */
    public void inicializaConAtletasYMarcasIniciales() throws NoAtletasInscritosException, NumInscripcionException, TipoEventoException, MarcaNegativaException {
     if (atletas.isEmpty()) {
         throw new NoAtletasInscritosException("No hay atletas inscritos.");
     }
        // Codigo para poblar atletas y marcas
        // Se abre el periodo de inscripciones
        inscribirAtleta("Kevin Mayer", "FRA");      // 800 pts por prueba
        inscribirAtleta("Larbi Bourrada", "ALG");   // 1000 pts por prueba
        inscribirAtleta("Dimitriy Karpov", "KAZ");   // 900 pts por prueba
        inscribirAtleta("Ashton Eaton", "USA");     // 700 pts por prueba
        inscribirAtleta("Ashley Moloney", "AUS");   // se lesionó y no compitió :-(
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
        // Ashley Moloney se lesionó y no competirá :-/

    }

}
