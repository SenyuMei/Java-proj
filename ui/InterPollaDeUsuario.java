package edu.upc.etsetb.poo.decathlon1.iu;

import edu.upc.etsetb.poo.decathlon1.casosdeuso.Controlador;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * La interficie de usuario del programa
 */
/*
 * https://en.wikipedia.org/wiki/Decathlon
 * https://es.wikipedia.org/wiki/Decatl%C3%B3n
 */
public class InterficieUsuario {

    /**
     * Lector utilizado para leer los comandos introducidos por el usuario.
     */
    private Scanner lector;

    /**
     * El controlador.
     */
    private final Controlador controlador;

    /**
     * La consola del sistema. Siempre debe usarse para mostrar la información
     * al usuario.
     */
    private final PrintStream console = System.out;

    /**
     * Separador utilizado en los comandos de entrada.
     */
    public static final String SEPARADOR = ":";

    /**
     * Mensaje de error que notifica que no hay atletas inscritos.
     */
    public static final String NO_ATLETAS_INSCRITOS_STR
            = "ERROR: Aún no hay ningún atleta inscrito.";
    /**
     * Mensaje de error que notifica que el número de atletas es erróneo
     */
    public static final String NUM_ATLETAS_ERRONEO_STR
            = "ERROR: Número de atletas erróneo.";
    /**
     * Mensaje de error que notifica que el número de inscripción es erróneo.
     */
    public static final String NUM_INSCRIPCION_ERRONEO_STR
            = "ERROR: El número de inscripción es erróneo.";

    /**
     * Mensaje de error que notifica que el tipo de evento es erróneo.
     */
    public static final String TIPO_DE_EVENTO_ERRONEO_STR
            = "ERROR: Número de tipo de evento erróneo.";

    /**
     * Mensaje de error que notifica que el código de retorno del método es
     * ilegal.
     */
    public static final String CODIGO_ILEGAL_STR
            = "ERROR: Código ilegal.";

    /**
     * Mensaje de error que notifica que el comando de entrada es erróneo.
     */
    public static final String CMD_ERRONEO_STR
            = "ERROR: Comando erróneo!";

    /**
     * Mensaje que notifica que la marca se ha añadido correctamente.
     */
    public static final String MARCA_ANYADIDA_STR = "Marca añadida";

    /**
     * Constructor; recibe un string con el nombre de la competición, su fecha y
     * su lugar, y una indicación de si el controlador debe inicializar el grupo
     * de atletas y marcas invocando al método
     * inicializaConAtletasYMarcasIniciales() o no; con esta información crea el
     * objeto Controlador y hace que el atributo 'controlador' lo referencie.
     *
     * @param detallesCompeticion String con nombre, fecha y lugar separados por
     * el caracter ':'
     * @param contrInitAtletasYMarcas true si se desea que el controlador debe
     * inicializar el grupo de atletas y marcas o no.
     */
    public InterficieUsuario(String detallesCompeticion, boolean contrInitAtletasYMarcas) {

        String[] partes = detallesCompeticion.split(SEPARADOR);
        String nombre = partes[0];
        String fecha = partes[1];
        String lugar = partes[2];
        this.controlador = new Controlador(nombre, fecha, lugar, this, contrInitAtletasYMarcas);
    }

    /**
     * Inscribe un atleta con el nombre y la nacionalidad pasados en la segunda
     * y tercera posición del argumento args (que es un array de Strings).
     *
     * @param args Un array de Strings que en su segunda y tercera posición
     * tiene el nombre y la nacionalidad de un atleta respectivamente.
     */
    public void inscribirAtleta(String[] args) {
        String nombre = args[1];
        String nacionalidad = args[2];
        controlador.inscribirAtleta(nombre, nacionalidad);
    }

    /**
     * Obtiene los datos de la competición y los presenta por pantalla.
     */
    public void mostrarCompeticion() {
        System.out.println(controlador.getInfoCompeticion());
    }

    /**
     * A este método se le pasa la representación textual de un número de
     * inscripción en la segunda posición de args (que es un array de Strings) y
     * muestra por consola la información del atleta con ese número de
     * inscripción * @param args Un array de Strings que en su segunda posición
     * tiene la representación textual del número de inscripción.
     *
     * @param args
     */
    public void mostrarAtleta(String[] args) {
        int numInscripcion = Integer.parseInt(args[1]);
        System.out.println(controlador.getInfoAtleta(numInscripcion));
    }

    /**
     * A este método se le pasan las representaciones textuales de un número de
     * inscripción, del identificador de un evento y de una marca; con esta
     * información el método añade una marca para un evento en el atleta con ese
     * número de inscripción; si todo va bien, muestra un mensaje que así lo
     * indica por consola; por el contrario, si se da alguna de las situaciones
     * que siguen:<br>
     * no hay ningún atleta inscrito<br>
     * el número de inscripción es erróneo<br>
     * el identificador del evento es erróneo<br>
     * El método muestra por consola un mensaje indicativo del error encontrado.
     * Debéis utilizar las constantes de tipo String de la clase
     * InterficieUsuario para ello.
     *
     * @param args Las representaciones textuales: del número de inscripción,
     * del identificador de un evento y de una marca, en la segunda, tercera y
     * cuarta posición del array args, respectivamente.
     */
    // HASTA AQUI ESTA TODO BIEN 
    public void anyadirMarcaEnEventoDeUnAtleta(String[] args) {

        int numInscripcion = Integer.parseInt(args[1]);
        int evento = Integer.parseInt(args[2]);
        double marca = Double.parseDouble(args[3]);
        int resultado = controlador.anyadirMarcaEnEventoDeUnAtleta(numInscripcion, evento, marca);

        switch (resultado) {
            case Controlador.RESULTADO_OK:
                this.console.println(InterficieUsuario.MARCA_ANYADIDA_STR);
                break;
            case Controlador.NO_ATLETAS_INSCRITOS:
                this.console.println(InterficieUsuario.NO_ATLETAS_INSCRITOS_STR);
                break;
            case Controlador.NUM_INSCRIPCION_ERRONEO:
                this.console.println(InterficieUsuario.NUM_INSCRIPCION_ERRONEO_STR);
                break;
            case Controlador.TIPO_DE_EVENTO_ERRONEO:
                this.console.println(InterficieUsuario.TIPO_DE_EVENTO_ERRONEO_STR);
                break;
            default:
                this.console.println(InterficieUsuario.CODIGO_ILEGAL_STR);
        }

    }

    /**
     * A este argumento se le pasa la representación textual de un número entero
     * en la segunda posición de args (que es un array de Strings); con ese
     * valor, el método muestra la clasificación de N atletas por consola,
     * siendo N el número representado en args; por el contrario, muestra un
     * mensaje de error si:<br>
     * Aún no hay ningúnatleta inscrito,<br>
     * El número de atletas que se quiere que aparezca en la clasificación es
     * erróneo (menor que 1 o mayor que el número de atletas compitiendo).
     *
     * @param args Los argumentos del comando.
     */
    public void mostrarClasificacion(String[] args) {
        int numAtletas = Integer.parseInt(args[1]);

        if (this.controlador == null) {
            this.console.println("Error: El controlador no está inicializado.");
            return;
        }

        String clasificacion = this.controlador.getClasificacion(numAtletas);
        if (clasificacion == null) {
            this.console.println("Error: La clasificación ha devuelto null.");
            return;
        }
        if (clasificacion.equals(InterficieUsuario.NO_ATLETAS_INSCRITOS_STR)) {
            this.console.println(InterficieUsuario.NO_ATLETAS_INSCRITOS_STR);
            return;
        }
        if (clasificacion.equals(InterficieUsuario.NUM_ATLETAS_ERRONEO_STR)) {
            this.console.println(InterficieUsuario.NUM_ATLETAS_ERRONEO_STR);
            return;
        }

        this.console.println(clasificacion);
    }

    /**
     * Presenta por consola el menú del programa.
     */
    public void mostrarOpciones() {
        this.console.println(
                SEPARADOR.repeat(30) + "\n"
                + "MENÚ DE OPCIONES:\n"
                + SEPARADOR.repeat(30) + "\n"
                + "ia:nombre:nacionalidad  - Inscribir atleta\n"
                + "mc                      - Mostrar información de la competición\n"
                + "ma:numInscripción       - Mostrar información de un atleta\n"
                + "am:numInsc:evento:marca - Añadir marca a un evento\n"
                + "cl:numAtletas          - Mostrar clasificación\n"
                + "salir                  - Terminar el programa\n"
                + SEPARADOR.repeat(30)
        );
    }

    /**
     * Este método recibe un comando y lo ejecuta. Si el comando pasado como
     * parámetro es erróneo, el método debe indicarlo mediante un mensaje.
     * Utilizad la constante de tipo String definida en la clase para ello.
     *
     * @param comando String con el comando.
     */
    public void ejecutaComando(String comando) { // ESTE ESTA MAL

        if (comando == null || comando.isBlank()) {
            this.console.println(CMD_ERRONEO_STR);
            return;
        }
        String[] partes = comando.split(SEPARADOR);
        String cmd = partes[0].toLowerCase();

        switch (cmd) {
            case "ia":
                if (partes.length != 3) {
                    this.console.println(CMD_ERRONEO_STR);
                    return;
                }
                controlador.inscribirAtleta(partes[1], partes[2]);
                break;
            case "mc":
                controlador.getInfoCompeticion();
                break;
            case "ma":
                if (partes.length != 2) {
                    this.console.println(CMD_ERRONEO_STR);
                    return;
                }
                controlador.getInfoAtleta(Integer.parseInt(partes[1]));
                break;
            case "am":
                if (partes.length != 4) {
                    this.console.println(CMD_ERRONEO_STR);
                    return;
                }
                int numInscripcion = Integer.parseInt(partes[1]);
                int evento = Integer.parseInt(partes[2]);
                double marca = Double.parseDouble(partes[3]);
                controlador.anyadirMarcaEnEventoDeUnAtleta(numInscripcion, evento, marca);
                break;
            case "cl":
                if (partes.length != 2) {
                    this.console.println(CMD_ERRONEO_STR);
                    return;
                }
                controlador.getClasificacion(Integer.parseInt(partes[1]));
                break;
            default:
                this.console.println(CMD_ERRONEO_STR);
                break;
        }
    }

    /**
     * Muestra el menú y va pidiendo y ejecutando los comandos que el usuario va
     * introduciendo hasta que se introduce el comando de acabar la ejecución
     * del programa.
     */
    public void start() {
        throw new UnsupportedOperationException("InterficieUsuario::inicializaConAtletasYMarcasInicialesDesdeArchivo(...) NO se ha implementado");
    }

}
