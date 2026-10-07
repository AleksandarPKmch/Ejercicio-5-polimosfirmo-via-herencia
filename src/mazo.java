import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class mazo {
    private final List<carta> cartas;
    private final Random random;

    public mazo() {
        cartas = new ArrayList<>();
        random = new Random();
    }

    public mazo(List<carta> cartas, Random random) {
        this.cartas = cartas;
        this.random = random;
    }

    public void agregarCarta(carta carta) {
        if (carta != null) cartas.add(carta);
    }

    public carta tomarCarta() {
        if (cartas.isEmpty()) return null;
        return cartas.remove(random.nextInt(cartas.size()));
    }

    public List<carta> listarCartas() {
        return Collections.unmodifiableList(cartas);
    }

    public carta buscarPorId(int id) {
        for (carta carta : cartas) {
            if (carta.getId() == id) return carta;
        }
        return null;
    }

    public List<carta> buscarPorNombre(String nombre) {
        List<carta> resultados = new ArrayList<>();
        String criterio = nombre.toLowerCase().trim();
        for (carta carta : cartas) {
            if (carta.getNombre().toLowerCase().contains(criterio)) {
                resultados.add(carta);
            }
        }
        return resultados;
    }

    public void ordenarPorCostoEnergia() {
        cartas.sort(Comparator.comparingInt(carta::getCostoEnergia));
    }

    public int cantidadCartas() { return cartas.size(); }

    public void generarCartasAleatorias(int cantidad) {
        String[] departamentos = {"Computacion", "Matematica", "Fisica", "Humanidades"};
        String[] profesores = {"Ing. Byte", "Lic. Vector", "Dra. Lambda", "MSc. Pixel"};
        String[] cursos = {"Programacion", "Calculo", "Bases de Datos", "Fisica", "Algoritmos"};
        String[] eventos = {"Semana de Parciales", "Feria de Clubes", "Hackathon", "Semana Cultural"};

        for (int id = 1; id <= cantidad; id++) {
            int tipo = random.nextInt(3);
            int costo = random.nextInt(5) + 1;

            switch (tipo) {
                case 0 ->                     {
                        String nombre = profesores[random.nextInt(profesores.length)] + " #" + id;
                        agregarCarta(new cartaCatedratico(id, nombre,
                                "Catedratico que modifica el ritmo academico.", costo,
                                departamentos[random.nextInt(departamentos.length)],
                                random.nextInt(6) + 1, (random.nextInt(5) + 1) * 10));
                    }
                case 1 ->                     {
                        String nombre = cursos[random.nextInt(cursos.length)] + " #" + id;
                        agregarCarta(new cartaCurso(id, nombre,
                                "Curso universitario que aporta creditos.", costo,
                                random.nextInt(5) + 1, random.nextInt(5) + 1));
                    }
                default ->                     {
                        String nombre = eventos[random.nextInt(eventos.length)] + " #" + id;
                        int cambio = random.nextInt(7) - 3;
                        agregarCarta(new cartaEventoCampus(id, nombre,
                                "Situacion especial que afecta la energia del jugador.", costo,
                                "Campus", cambio));
                    }
            }
        }
    }
}
