import java.util.List;

/**
 * Clase que se encarga de gestional los hilos de ejecucion de la carrera
 */
public class Race {

    private List<Turtle> turtles;

    /**
     * Constructor. Recibe la lista de tortugas e inicia la carrera
     * @param turtles
     */
    public Race(List<Turtle> turtles){
        this.turtles = turtles;
        startRace();
    }

    /**
     * Metodo que inicializa todas las tortugas (los hilos)
     */
    private void startRace(){
        System.out.println("################ START RACE ###################");
        for (Turtle t: turtles){
            t.start();
        }
        Turtle.interrupted();
    }
}
