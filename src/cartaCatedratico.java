public class cartaCatedratico extends carta {
    private final String departamento;
    private final int llamadasAtencion;
    private final int tiempoAtencion;

    public cartaCatedratico(int id, String nombre, String descripcion, int costoEnergia,
                            String departamento, int llamadasAtencion, int tiempoAtencion) {
        super(id, nombre, descripcion, costoEnergia);
        this.departamento = departamento;
        this.llamadasAtencion = llamadasAtencion;
        this.tiempoAtencion = tiempoAtencion;
    }

    public String getDepartamento() { return departamento; }
    public int getLlamadasAtencion() { return llamadasAtencion; }
    public int getTiempoAtencion() { return tiempoAtencion; }

    @Override
    public String jugarCarta(tablero tablero, jugador jugador) {
        tablero.agregarCarta(this);
        int energiaRecuperada = Math.max(1, tiempoAtencion / 10);
        jugador.modificarEnergia(energiaRecuperada);
        return getNombre() + " entro al tablero. Su tiempo de atencion recupera "
                + energiaRecuperada + " punto(s) de energia.";
    }

    @Override
    public String mostrarInformacion() {
        return "[CATEDRATICO] " + super.mostrarInformacion()
                + " | Departamento: " + departamento
                + " | Llamadas de atencion: " + llamadasAtencion
                + " | Tiempo de atencion: " + tiempoAtencion + " min";
    }

    @Override
    public String jugarCarta1(tablero tablero, jugador jugador) {
        return jugarCarta(tablero, jugador);
    }

    @Override
    public String jugarCarta(tablero tablero) {
        return "El catedratico requiere un jugador para ejecutarse.";
    }
}
