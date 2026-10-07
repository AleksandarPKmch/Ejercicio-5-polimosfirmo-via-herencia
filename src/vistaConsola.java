import java.util.List;
import java.util.Scanner;

public class vistaConsola {
    private final Scanner scanner;

    public vistaConsola() { scanner = new Scanner(System.in); }

    public void mostrarMenuPrincipal() {
        System.out.println("\n=== JUEGO DE CARTAS - VIDA ACADEMICA ===");
        System.out.println("1. Listar cartas del mazo");
        System.out.println("2. Buscar carta por ID");
        System.out.println("3. Buscar carta por nombre");
        System.out.println("4. Ordenar mazo por costo de energia");
        System.out.println("5. Jugar por turnos");
        System.out.println("0. Salir");
    }

    public void mostrarMenuTurno() {
        System.out.println("\n--- ACCIONES DEL TURNO ---");
        System.out.println("1. Usar una carta");
        System.out.println("2. Tomar una carta");
        System.out.println("3. Pasar turno");
        System.out.println("0. Volver al menu principal");
    }

    public void mostrarCartas(List<carta> cartas) {
        if (cartas.isEmpty()) {
            System.out.println("No hay cartas para mostrar.");
            return;
        }
        for (carta carta : cartas) System.out.println(carta.mostrarInformacion());
    }

    public void mostrarInformacionCarta(carta carta) {
        System.out.println(carta == null ? "Carta no encontrada." : carta.mostrarInformacion());
    }

    public void mostrarEstadoTablero(tablero tablero) { System.out.println(tablero.obtenerEstado()); }

    public void mostrarManoJugador(jugador jugador) {
        System.out.println("\nJugador: " + jugador.getNombre() + " | Energia: " + jugador.getEnergia()
                + " | Creditos: " + jugador.getCreditos());
        List<carta> mano = jugador.getMano();
        if (mano.isEmpty()) {
            System.out.println("Tu mano esta vacia.");
            return;
        }
        for (int i = 0; i < mano.size(); i++) {
            System.out.println((i + 1) + ". " + mano.get(i).mostrarInformacion());
        }
    }

    public int solicitarEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine();
            try { return Integer.parseInt(entrada.trim()); }
            catch (NumberFormatException e) { System.out.println("Ingresa un numero valido."); }
        }
    }

    public String solicitarEntrada(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }

    public void mostrarMensaje(String mensaje) { System.out.println(mensaje); }
}
