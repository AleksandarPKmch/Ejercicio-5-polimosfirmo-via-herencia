import java.util.List;

public class controladorJuego {
    private final mazo mazo;
    private final tablero tablero;
    private final jugador jugador;
    private final vistaConsola vista;
    private boolean ejecutando;

    public controladorJuego() {
        mazo = new mazo();
        tablero = new tablero();
        jugador = new jugador("Estudiante", 10);
        vista = new vistaConsola();
        ejecutando = true;
    }

    public controladorJuego(jugador jugador, mazo mazo, tablero tablero, vistaConsola vista) {
        this.jugador = jugador;
        this.mazo = mazo;
        this.tablero = tablero;
        this.vista = vista;
    }

    public void iniciarJuego() {
        cargarDatosIniciales();
        vista.mostrarMensaje("Catalogo inicial creado con " + mazo.cantidadCartas() + " cartas.");

        while (ejecutando) {
            vista.mostrarMenuPrincipal();
            procesarOpcion(vista.solicitarEntero("Selecciona una opcion: "));
        }
    }

    private void cargarDatosIniciales() {
        mazo.generarCartasAleatorias(15);
    }

    private void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1 -> listarCartas();
            case 2 -> buscarCartaPorId();
            case 3 -> buscarCartaPorNombre();
            case 4 -> ordenarMazoPorCosto();
            case 5 -> jugarTurnos();
            case 0 -> {
                ejecutando = false;
                vista.mostrarMensaje("Gracias por jugar.");
            }
            default -> vista.mostrarMensaje("Opcion invalida.");
        }
    }

    private void listarCartas() { vista.mostrarCartas(mazo.listarCartas()); }

    private void buscarCartaPorId() {
        int id = vista.solicitarEntero("ID de la carta: ");
        vista.mostrarInformacionCarta(mazo.buscarPorId(id));
    }

    private void buscarCartaPorNombre() {
        String nombre = vista.solicitarEntrada("Nombre o parte del nombre: ");
        List<carta> resultados = mazo.buscarPorNombre(nombre);
        if (resultados.isEmpty()) vista.mostrarMensaje("No se encontraron cartas.");
        else vista.mostrarCartas(resultados);
    }

    private void ordenarMazoPorCosto() {
        mazo.ordenarPorCostoEnergia();
        vista.mostrarMensaje("Mazo ordenado de menor a mayor costo de energia.");
        vista.mostrarCartas(mazo.listarCartas());
    }

    private void jugarTurnos() {
        if (jugador.getMano().isEmpty()) {
            for (int i = 0; i < 3 && mazo.cantidadCartas() > 0; i++) jugador.tomarCarta(mazo);
            vista.mostrarMensaje("Se entregaron 3 cartas iniciales al jugador.");
        }

        boolean enPartida = true;
        int turno = 1;
        while (enPartida) {
            vista.mostrarMensaje("\n========== TURNO " + turno + " ==========");
            vista.mostrarManoJugador(jugador);
            vista.mostrarEstadoTablero(tablero);
            vista.mostrarMenuTurno();
            int opcion = vista.solicitarEntero("Accion: ");

            switch (opcion) {
                case 1 -> usarCarta();
                case 2 -> {
                    if (mazo.cantidadCartas() == 0) vista.mostrarMensaje("El mazo esta vacio.");
                    else {
                        jugador.tomarCarta(mazo);
                        vista.mostrarMensaje("Tomaste una carta. Termina tu turno.");
                        turno++;
                    }
                }
                case 3 -> {
                    jugador.modificarEnergia(2);
                    vista.mostrarMensaje("Pasaste el turno y recuperaste 2 puntos de energia.");
                    turno++;
                }
                case 0 -> enPartida = false;
                default -> vista.mostrarMensaje("Opcion invalida.");
            }
        }
    }

    private void usarCarta() {
        if (jugador.getMano().isEmpty()) {
            vista.mostrarMensaje("No tienes cartas en la mano.");
            return;
        }
        vista.mostrarManoJugador(jugador);
        int seleccion = vista.solicitarEntero("Numero de carta a jugar: ");
        String resultado = jugador.usarCarta(seleccion - 1, tablero, jugador);
        vista.mostrarMensaje(resultado);
    }

    public vistaConsola getVista() {
        return vista;
    }
}
