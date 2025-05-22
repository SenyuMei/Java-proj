package edu.upc.etsetb.poo.decathlon1.iu;

import edu.upc.etsetb.poo.decathlon1.casosdeuso.Controlador;
import edu.upc.etsetb.poo.decathlon1.dominio.MarcaNegativaException;
import edu.upc.etsetb.poo.decathlon1.dominio.NoAtletasInscritosException;
import edu.upc.etsetb.poo.decathlon1.dominio.NumInscripcionException;
import edu.upc.etsetb.poo.decathlon1.dominio.NumeroDeAtletasException;
import edu.upc.etsetb.poo.decathlon1.dominio.TipoEventoException;
import java.io.IOException;
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
     * @throws edu.upc.etsetb.poo.decathlon1.dominio.NoAtletasInscritosException
     * @throws edu.upc.etsetb.poo.decathlon1.dominio.NumInscripcionException
     * @throws edu.upc.etsetb.poo.decathlon1.dominio.TipoEventoException
     * @throws edu.upc.etsetb.poo.decathlon1.dominio.MarcaNegativaException
     */
    public InterficieUsuario(String detallesCompeticion, boolean contrInitAtletasYMarcas)
        throws NoAtletasInscritosException, NumInscripcionException, TipoEventoException, MarcaNegativaException{
       
      //throw new UnsupportedOperationException("El controloador de iu ancara no s'ha implementat");
        String[] partes = detallesCompeticion.split(SEPARADOR);

        String nombre = partes[0];
        String fecha = partes[1];
        String lugar = partes[2];

       this.controlador = new Controlador(nombre, fecha, lugar, this, contrInitAtletasYMarcas);
       
     
       
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
        //throw new UnsupportedOperationException("InterficieUsuario::inscribirAtleta(...) NO se ha implementado");

        String nombre = args[1].trim();
        String nacionalidad = args[2].trim();

        if (nombre.isEmpty() || nacionalidad.isEmpty()) {
            console.println(CMD_ERRONEO_STR);
            return;
        }

        controlador.inscribirAtleta(nombre, nacionalidad);
    }
   

    /**
     * Obtiene los datos de la competición y los presenta por pantalla.
     */
    public void mostrarCompeticion() {
      // throw new UnsupportedOperationException("InterficieUsuario::mostrarCompeticion(...) NO se ha implementado");
       this.console.println(this.controlador.getInfoCompeticion());
    }

    /**
     * A este método se le pasa la representación textual de un número de inscripción
     * en la segunda posición de args (que es un array de Strings) y muestra por
     * consola la información del atleta con ese número de inscripción
     *      *
     * @param args Un array de Strings que en su segunda posición tiene la
     * representación textual del número de inscripción.
     */
    public void mostrarAtleta(String[] args) throws NumInscripcionException{
        //throw new UnsupportedOperationException("InterficieUsuario::mostrarAtleta(...) NO se ha implementado");
        int numInscripcion = Integer.parseInt(args[1]);
        console.println(this.controlador.getInfoAtleta(numInscripcion));  
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
    public void anyadirMarcaEnEventoDeUnAtleta(String[] args) throws NoAtletasInscritosException, NumInscripcionException, TipoEventoException, MarcaNegativaException{
        //throw new UnsupportedOperationException("InterficieUsuario::anyadirMarcaEnEventoDeUnAtleta(...) NO se ha implementado");
        /*int numInscripcion = Integer.parseInt(args[1]);
        int evento = Integer.parseInt(args[2]);
        double marca = Double.parseDouble(args[3]);
       
        int aux = controlador.anyadirMarcaEnEventoDeUnAtleta(numInscripcion, evento, marca);
       
        switch(aux){
            case Controlador.NO_ATLETAS_INSCRITOS:
                console.println(NO_ATLETAS_INSCRITOS_STR);
                break;
            case Controlador.NUM_INSCRIPCION_ERRONEO:
                console.println(NUM_INSCRIPCION_ERRONEO_STR);
                break;
            case Controlador.TIPO_DE_EVENTO_ERRONEO:
                console.println(TIPO_DE_EVENTO_ERRONEO_STR);
                break;
            case Controlador.RESULTADO_OK:
                console.println(MARCA_ANYADIDA_STR);
                break;
        }
        */
        int numInscripcion = Integer.parseInt(args[1]);
        int evento = Integer.parseInt(args[2]);
        double marca = Double.parseDouble(args[3]);
       

        try {
            controlador.anyadirMarcaEnEventoDeUnAtleta(numInscripcion, evento, marca);
            console.println(MARCA_ANYADIDA_STR);
        } catch (NoAtletasInscritosException | NumInscripcionException | TipoEventoException | MarcaNegativaException ex) {
            console.println(ex.getMessage());
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
       
       
    public void mostrarClasificacion(String[] args) throws NumeroDeAtletasException, NoAtletasInscritosException{
        //throw new UnsupportedOperationException("InterficieUsuario::mostrarClasificacion(...) NO se ha implementado");
        int n = Integer.parseInt(args[1]);
        console.println(controlador.getClasificacion(n));
    }

    /**
     * Presenta por consola el menú del programa.
     */
    public void mostrarOpciones() {
        //throw new UnsupportedOperationException("InterficieUsuario::mostrarOpciones(...) NO se ha implementado");
        console.println("Opciones:");
        console.println("ay\t\t\t\t\tAyuda");
        console.println("ia:nombre:nacionalidad\t\t\tInscribir atleta");
        console.println("mc\t\t\t\t\tMostrar datos de la competición");
        console.println("ma:num_atleta\t\t\t\tMostrar datos de un atleta");
        console.println("am:num_atleta:num_evento:marca\t\tAñadir marca de un evento a un atleta");
        console.println("cl:num_atletas\t\t\t\tMostrar clasificación");
        console.println("fi\t\t\t\t\tFin\n");
        console.println("Lista de eventos:");
        console.println("[0] 100 metros lisos");
        console.println("[1] Salto de longitud");
        console.println("[2] Lanzamiento de peso");
        console.println("[3] Salto de altura");
        console.println("[4] 400 metros lisos\n");
    }

    /**
     * Este método recibe un comando y lo ejecuta. Si el comando pasado
     * como parámetro es erróneo, el método debe indicarlo mediante un
     * mensaje. Utilizad la constante de tipo String definida en la clase
     * para ello.
     *
     * @param comando String con el comando.
     */
    public void ejecutaComando(String comando) throws NoAtletasInscritosException, NumeroDeAtletasException, NumInscripcionException, NumInscripcionException, MarcaNegativaException, TipoEventoException{
        //throw new UnsupportedOperationException("InterficieUsuario::ejecutaComando(...) NO se ha implementado");
       
        String[] partes = comando.split(SEPARADOR);
       
        if (partes.length == 0) {
            console.println(CMD_ERRONEO_STR);
            return;
        }

        // AM:num_atleta:num_evento:marca
        switch (partes[0].toLowerCase()) {
            case "am":
                if (partes.length != 4) {
                    console.println(CMD_ERRONEO_STR);
                } else {
                    anyadirMarcaEnEventoDeUnAtleta(partes);
                }
                break;

            case "cl":
                if (partes.length != 2) {
                    console.println(CMD_ERRONEO_STR);
                } else {
                    mostrarClasificacion(partes);
                }
                break;

            case "ma":
                if (partes.length != 2) {
                    console.println(CMD_ERRONEO_STR);
                } else {
                    mostrarAtleta(partes);
                }
                break;

            case "mc":
                controlador.getInfoCompeticion();
                break;

            case "ia":
                if (partes.length != 3) {
                    console.println(CMD_ERRONEO_STR);
                } else {
                    inscribirAtleta(partes);
                }
                break;

            case "fi":
                console.println("\nFin del programa. ✦ Hasta la proxima ✦\n");
                break;

            case "ay":
                mostrarOpciones();
                break;

            default:
                console.println(CMD_ERRONEO_STR);
                break;
        }
    }

    /**
     * Muestra el menú y va pidiendo y ejecutando los comandos que el usuario va
     * introduciendo hasta que se introduce el comando de acabar la ejecución
     * del programa.
     */
    public void start() throws NoAtletasInscritosException, NumeroDeAtletasException, NumInscripcionException, MarcaNegativaException, TipoEventoException{
        //throw new UnsupportedOperationException("InterficieUsuario::inicializaConAtletasYMarcasInicialesDesdeArchivo(...) NO se ha implementado");
        boolean continuar = true;

        while (continuar) {
            console.print("Entra un comando\n>");
            String comando = lector.nextLine().trim();
            System.out.println("\n");

            if (comando.equalsIgnoreCase("fi")) {
                continuar = false;
                console.println("Fin del programa.");
            } else {
                ejecutaComando(comando);
            }
        }
    }
   
    public void inicializaConAtletasYMarcasInicialesDesdeArchivo()
        throws NoAtletasInscritosException, NumInscripcionException, TipoEventoException, MarcaNegativaException, IOException{
       
        throw new UnsupportedOperationException("inicializaConAtletasYMarcasInicialesDesdeArchivo ancara no s'ha implementat");

    }
}
