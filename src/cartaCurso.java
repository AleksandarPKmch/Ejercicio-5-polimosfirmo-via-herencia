public class cartaCurso extends carta {
    private final int creditos;
    private final int dificultad;

    public cartaCurso(int id, String nombre, String descripcion, int costoEnergia,
                       int creditos, int dificultad) {
        super(id, nombre, descripcion, costoEnergia);
        this.creditos = creditos;
        this.dificultad = dificultad;
    }

    public int getCreditos() { return creditos; }
    public int getDificultad() { return dificultad; }

    @Override
    public String jugarCarta(tablero tablero, jugador jugador) {
        if (jugador == null) {
            return getNombre() + " requiere un jugador para ejecutarse.";
        }
        tablero.agregarCarta(this);
        jugador.agregarCreditos(creditos);
        return getNombre() + " fue cursado. El jugador obtiene " + creditos
                + " credito(s). Dificultad: " + dificultad + ".";
    }

    @Override
    public String mostrarInformacion() {
        return "[CURSO] " + super.mostrarInformacion()
                + " | Creditos: " + creditos
                + " | Dificultad: " + dificultad + "/5";
    }

    @Override
    public String jugarCarta1(tablero tablero, jugador jugador) {
        return jugarCarta(tablero, jugador);
    }
}
