import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class tablero {
    private final List<carta> cartasEnTablero;

    public tablero() {
        cartasEnTablero = new ArrayList<>();
    }

    public void agregarCarta(carta carta) {
        if (carta != null) cartasEnTablero.add(carta);
    }

    public void removerCarta(carta carta) { cartasEnTablero.remove(carta); }
    public List<carta> getCartasEnTablero() { return Collections.unmodifiableList(cartasEnTablero); }
    public void limpiar() { cartasEnTablero.clear(); }

    public String obtenerEstado() {
        if (cartasEnTablero.isEmpty()) return "El tablero esta vacio.";
        StringBuilder sb = new StringBuilder("Cartas en el tablero:\n");
        for (carta carta : cartasEnTablero) sb.append("- ").append(carta.getNombre()).append('\n');
        return sb.toString();
    }
}
