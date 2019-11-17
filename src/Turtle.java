/**
 * Clase POJO
 */
public class Turtle {

    private String name;
    private int dorsal;

    public Turtle(){ }

    public Turtle(String name, int dorsal) {
        this.name = name;
        this.dorsal = dorsal;
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
