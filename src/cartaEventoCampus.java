public class cartaEventoCampus extends carta {
    private final String tipoEvento;
    private final int cambioEnergia;

    public cartaEventoCampus(int id, String nombre, String descripcion, int costoEnergia,
                              String tipoEvento, int cambioEnergia) {
        super(id, nombre, descripcion, costoEnergia);
        this.tipoEvento = tipoEvento;
        this.cambioEnergia = cambioEnergia;
    }

    public String getTipoEvento() { return tipoEvento; }
    public int getCambioEnergia() { return cambioEnergia; }

    @Override
    public String jugarCarta(tablero tablero, jugador jugador) {
        tablero.agregarCarta(this);
        jugador.modificarEnergia(cambioEnergia);
        String efecto = cambioEnergia >= 0 ? "recupera " + cambioEnergia : "pierde " + Math.abs(cambioEnergia);
        return "Evento " + getNombre() + ": el jugador " + efecto + " punto(s) de energia.";
    }

    @Override
    public String mostrarInformacion() {
        return "[EVENTO CAMPUS] " + super.mostrarInformacion()
                + " | Tipo: " + tipoEvento
                + " | Cambio de energia: " + (cambioEnergia >= 0 ? "+" : "") + cambioEnergia;
    }

    @Override
    public String jugarCarta(tablero tablero) {
        return "El evento de campus requiere un jugador para ejecutarse.";
    }
}
