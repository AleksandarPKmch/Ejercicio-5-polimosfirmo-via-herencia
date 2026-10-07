
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class jugador {
    private final String nombre;
    private int energia;
    private int creditos;
    private final List<carta> mano;

    public jugador(String nombre, int energiaInicial) {
        this.nombre = nombre;
        this.energia = energiaInicial;
        this.creditos = 0;
        this.mano = new ArrayList<>();
    }

    public String getNombre() { return nombre; }
    public int getEnergia() { return energia; }
    public int getCreditos() { return creditos; }
    public List<carta> getMano() { return Collections.unmodifiableList(mano); }

    public void modificarEnergia(int valor) {
        energia = Math.max(0, energia + valor);
    }

    public void agregarCreditos(int cantidad) {
        if (cantidad > 0) creditos += cantidad;
    }

    public void tomarCarta(mazo mazo) {
        carta carta = mazo.tomarCarta();
        if (carta != null) mano.add(carta);
    }

    public String usarCarta(int indice, tablero tablero, jugador jugador) {
        if (indice < 0 || indice >= mano.size()) return "Seleccion de carta invalida.";
        carta carta = mano.get(indice);
        if (energia < carta.getCostoEnergia()) return "No tienes energia suficiente para jugar esa carta.";

        energia -= carta.getCostoEnergia();
        mano.remove(indice);
        return carta.jugarCarta(tablero, jugador);
    }
}
