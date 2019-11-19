import java.util.Random;

/**
 * Clase POJO que forma una tortuga
 * Extiende de la clase Thread por lo que se pueden crear hilo de la misma
 */
public class Turtle extends Thread {

    private String name;
    private int dorsal;
    public static boolean exit = false;         // Creamos una variable de clase
    public static String winner = "";

    public Turtle(){ }
    public Turtle(String name, int dorsal) {
        this.name = name;
        this.dorsal = dorsal;
    }

    @Override
    public void run() {
        try {
            System.out.println("Sale la tortuga " + name);
            int valor = 0;
            do{
                sleep(100);
            }while(Utils.getRandom(valor, 500) != 500);        // Mientras el valor random no sea 500 se repite el bucle

            //
            if(!exit) {
                System.out.println();
                System.out.println("THE WINNER IS: " + name);
                winner = name;
                exit = true;
            }
            interrupt();

        } catch (InterruptedException e) {
            System.out.println(name + " ha perdido.");
        }
    }

    public String getNombre() {
        return name;
    }

    public void setNombre(String name) {
        this.name = name;
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    @Override
    public String toString() {
        return "Turtle{" +
                "name='" + name + '\'' +
                ", dorsal=" + dorsal +
                '}';
    }
}
