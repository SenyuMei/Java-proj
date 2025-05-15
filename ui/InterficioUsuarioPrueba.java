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
     * La consola del sistema. Siempre debe usarse para mostrar la información al usuario.
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
     * Mensaje de error que notifica que el código de retorno del método es ilegal.
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
     * su lugar, y una indicación de si el controlador debe inicializar el 
     * grupo de atletas y marcas invocando al método inicializaConAtletasYMarcasIniciales()
     * o no; con esta información crea el objeto Controlador y hace que el atributo 
     * 'controlador' lo referencie.
     *
     * @param detallesCompeticion String con  nombre, fecha y lugar separados por el caracter ':'
     * @param contrInitAtletasYMarcas true si se desea que el controlador debe inicializar
     * el grupo de atletas y marcas o no.
     */
    public InterficieUsuario(String detallesCompeticion, boolean contrInitAtletasYMarcas){
        
        this.lector = new Scanner(System.in);
        String[] parte = detallesCompeticion.split(SEPARADOR);
        
        String nombre = parte[0];
        String fecha = parte[1];
        String lugar = parte[2];
        
        this.controlador = new Controlador(nombre, fecha, lugar,this ,contrInitAtletasYMarcas);
    }
    /**
     * Inscribe un atleta con el nombre y la nacionalidad pasados en 
     * la segunda y tercera posición del argumento args (que es un array de 
     * Strings).
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
       controlador.getInfoCompeticion();
    }

    /**
     * A este método se le pasa la representación textual de un número de inscripción 
     * en la segunda posición de args (que es un array de Strings) y muestra por 
     * consola la información del atleta con ese número de inscripción
     *      *
     * @param args Un array de Strings que en su segunda posición tiene la 
     * representación textual del número de inscripción.
     */
    public void mostrarAtleta(String[] args) {
        int numeroInscripcion =  Integer.parseInt(args[1]);
        String infoAtleta = controlador.getInfoAtleta(numeroInscripcion);
        System.out.println(infoAtleta);
    }

    /**
     * A este método se le pasan las representaciones textuales de un número de 
     * inscripción, del identificador de un evento y de una marca; con esta 
     * información el método añade una marca para un evento en el atleta con ese 
     * número de inscripción; si todo va bien, muestra un mensaje que así lo indica 
     * por consola; por el contrario, si se da alguna de las situaciones que siguen:<br>
     *   no hay ningún atleta inscrito<br>
     *   el número de inscripción es erróneo<br>
     *   el identificador del evento es erróneo<br>
     * El método muestra por consola un mensaje indicativo del error encontrado.
     * Debéis utilizar las constantes de tipo String de la clase InterficieUsuario
     * para ello.
    *
     * @param args Las representaciones textuales: del número de inscripción, 
     * del identificador de un evento y de una marca, en la segunda, 
     * tercera y cuarta posición del array args, respectivamente.
     */
    public void anyadirMarcaEnEventoDeUnAtleta(String[] args) {
        
        int numInscripcion = Integer.parseInt(args[1]);
        int evento = Integer.parseInt(args[2]);
        double marca = Double.parseDouble(args[3]);
        
        int resultado = controlador.anyadirMarcaEnEventoDeUnAtleta(numInscripcion, evento, marca);
        
        if (resultado == Controlador.NO_ATLETAS_INSCRITOS) {
            System.out.println(NO_ATLETAS_INSCRITOS_STR);
        } else if (resultado == Controlador.NUM_INSCRIPCION_ERRONEO) {
            System.out.println(NUM_INSCRIPCION_ERRONEO_STR);         
        } else if (resultado == Controlador.TIPO_DE_EVENTO_ERRONEO) {
            System.out.println(TIPO_DE_EVENTO_ERRONEO_STR);
        } else if (resultado == Controlador.RESULTADO_OK) {
            System.out.println(MARCA_ANYADIDA_STR);
        } else {
            System.out.println(CODIGO_ILEGAL_STR);
        }
    }

    /**
     * A este argumento se le pasa la representación textual de un número 
     * entero  en la segunda posición de args (que es un array de Strings); 
     * con ese valor, el método muestra la clasificación de N atletas por 
     * consola, siendo N el número representado en args; por el contrario, muestra 
     * un mensaje de error si:<br>
     * Aún no hay ningúnatleta inscrito,<br>
     * El número de atletas que se quiere que aparezca en la clasificación 
     * es erróneo (menor que 1 o mayor que el número de atletas
     * compitiendo).
     *
     * @param args Los argumentos del comando.
     */
    public void mostrarClasificacion(String[] args) {
        int num = Integer.parseInt(args[1]);
        
        String resultado = controlador.getClasificacion(num);
        
        if (resultado.equals(NO_ATLETAS_INSCRITOS_STR) || resultado.equals(NUM_ATLETAS_ERRONEO_STR)) {
            System.out.println(resultado);
        } else {
            System.out.println(resultado);
        }
    }

    /**
     * Presenta por consola el menú del programa.
     */
    public void mostrarOpciones() {
        String s = "Opciones: \nay \tAyuda \n"
                + "ia:nombre:nacionalidad \tInscribir atleta \n"
                + "mc \tMostrar datos de la competición\n"
                + "ma:num_atleta \tMostrar datos de un atleta\n"
                + "am:num_atleta:num_evento:marca \tAñadir marca de un evento a un atleta \n"
                + "cl:num_atletas \tMostrar clasificación \n"
                + "fi \tFin";
        System.out.println(s);
    }

    /**
     * Este método recibe un comando y lo ejecuta. Si el comando pasado 
     * como parámetro es erróneo, el método debe indicarlo mediante un 
     * mensaje. Utilizad la constante de tipo String definida en la clase
     * para ello.
     *
     * @param comando String con el comando.
     */
    public void ejecutaComando(String comando) {
        String[] args = comando.split(SEPARADOR);
        
        switch (args[0]) {
            case "ay":
                mostrarOpciones();
                break;
            case "ia":
                if (args.length == 3) {
                    inscribirAtleta(args);
                } else {
                    System.out.println(CMD_ERRONEO_STR);
                }
                break;
            case "mc":
                if (args.length == 1) {
                    System.out.println(controlador.getInfoCompeticion());
                } else {
                    System.out.println(CMD_ERRONEO_STR);
                }
                break;
            case "ma":
                if (args.length == 2) {
                    mostrarAtleta(args);
                } else {
                    System.out.println(CMD_ERRONEO_STR);
                }
                break;
            case "am":
                if (args.length == 4) {
                    anyadirMarcaEnEventoDeUnAtleta(args);
                } else {
                    System.out.println(CMD_ERRONEO_STR);
                }
                break;
            case "cl":
                if (args.length == 2) {
                    mostrarClasificacion(args);
                } else {
                    System.out.println(CMD_ERRONEO_STR);
                }
                break;
            default:
                System.out.println(CMD_ERRONEO_STR);
                break;
        }
    }

    /**
     * Muestra el menú y va pidiendo y ejecutando los comandos que el usuario va
     * introduciendo hasta que se introduce el comando de acabar la ejecución
     * del programa.
     */
    public void start() {
        mostrarOpciones();
        String comando;

        while (true) {
            System.out.print("Entra un comando: ");
            comando = lector.nextLine();
                if (comando.equals("fi")) {
                    System.out.println("Fin del programa. ¡Hasta la próxima usuario!");
                break;
                }
        ejecutaComando(comando);
        }
    }
}
