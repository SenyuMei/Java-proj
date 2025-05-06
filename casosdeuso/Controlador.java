public int anyadirMarcaEnEventoDeUnAtleta(int numInscripcion, int evento, double marca) {
        
        if ( atletas == null ) {
            return NO_ATLETAS_INSCRITOS;
        } else if ( this.atletas.containsKey(numInscripcion) ) {
            return NUM_INSCRIPCION_ERRONEO;
        } else if ( evento < 0 || evento > MarcaEnEvento.NUM_EVENTOS ) {
            return TIPO_DE_EVENTO_ERRONEO;
        } else {
            return RESULTADO_OK;
        }
    }
    /**
     * Devuelve un String con la clasificación de los 'numAtletas' mejores
     * atletas si todo es correcto, pero devuelve un mensaje notificando un
     * error que comienza con "ERROR: " (ver return debajo) si: aún no hay
     * ningún atleta inscrito, el número de atletas que se quiere que aparezca
     * en la clasificación es erróneo (menor que 1 o mayor que el número de
     * atletas compitiendo)
     *
     * @param numAtletas Número de los 'n' mejores atletas que queremos que
     * aparecerán en la clasificación.
     * @return Devuelve<br>
     * Un String con la clasificación de los 'numAtletas' mejores atletas<br>
     * El String InterficieUsuario.NO_ATLETAS_INSCRITOS_STR si no se ha inscrito
     * ningún atleta<br>
     * El String InterficieUsuario.NUM_ATLETAS_ERRONEO_STR si el número de inscripción
     * pasado no es correcto
     */
    public String getClasificacion(int numAtletas) {
        Clasificacion c = new Clasificacion(numAtletas);
        this.clasificacion = c;
        if ( numAtletas < 1 || competicion.getNumInscritos() > numAtletas ) {
            String a = ("ERROR: " + NUM_INSCRIPCION_ERRONEO);
            return a;
        } else if ( numAtletas == 0 ) {
            String b = ("ERROR: " + NO_ATLETAS_INSCRITOS);
            return b;
        } else {
        return this.clasificacion.toString();
        }
    }
