public abstract class carta {
    private final int id;
    private final String nombre;
    private final String descripcion;
    private final int costoEnergia;

    public carta(int id, String nombre, String descripcion, int costoEnergia) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.costoEnergia = costoEnergia;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public int getCostoEnergia() { return costoEnergia; }

    public abstract String jugarCarta(tablero tablero, jugador jugador);

    public String jugarCarta(tablero tablero) {
        return "Esta carta requiere un jugador para ejecutarse.";
    }

    public String mostrarInformacion() {
        return "ID: " + id +
                " | Nombre: " + nombre +
                " | Costo: " + costoEnergia +
                " | Descripcion: " + descripcion;
    }

    public String jugarCarta1(tablero tablero, jugador jugador) {
        return jugarCarta(tablero, jugador);
    }
}
