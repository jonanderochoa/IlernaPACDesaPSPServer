/**
 * Clase POJO
 */
public class Tortuga {

    private String nombre;
    private int dorsal;

    public Tortuga(){ }

    public Tortuga(String nombre, int dorsal) {
        this.nombre = nombre;
        this.dorsal = dorsal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    @Override
    public String toString() {
        return "Tortuga{" +
                "nombre='" + nombre + '\'' +
                ", dorsal=" + dorsal +
                '}';
    }
}
