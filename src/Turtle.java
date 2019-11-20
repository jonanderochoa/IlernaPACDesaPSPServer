import java.util.Observable;

/**
 * Clase POJO que forma una tortuga
 * Como queremos que cuando llegue una tortuga, devuelva el nombre, usaremos la clase Observable. Y como queremos hacer
 * hilos pero ya hereda de una clase, implementamos la interfaz Runnable
 */
public class Turtle extends Observable implements Runnable {

    private String name;
    private int dorsal;

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
                Thread.sleep(100);

            }while(Utils.getRandom(valor, 500) != 500);        // Mientras el valor random no sea 500 se repite el bucle
            System.out.println(":D :D  THE WINNER IS: " + name + " :D :D");
            System.out.println();
            this.setChanged();
            this.notifyObservers(name);                        // Notifica a los observadores cuando hay un cambio
            this.clearChanged();

        } catch (InterruptedException e) {
            System.out.println(":(" + name + " ha perdido" + ":(");
        }
    }

    @Override
    public String toString() {
        return "Turtle{" +
                "name='" + name + '\'' +
                ", dorsal=" + dorsal +
                '}';
    }
}
