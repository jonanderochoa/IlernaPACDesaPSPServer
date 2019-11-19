import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de gestionar las tortugas y la lista que las contiene
 *
 * @project IlernaPACDesaPSPServer
 * @author: jonan on 17/11/2019
 */
public class ManageTurtle {

    private List<Turtle> turtles;

    public ManageTurtle(){
        turtles = new ArrayList<Turtle>();
    }

    public List<Turtle> getTurtles(){
        return turtles;
    }

    /**
     * Muestra todas las tortugas que contiene el arrayList
     */
    public void showAllTurtles(){
        for (Turtle t: turtles){
            System.out.println(t);
        }
    }

    /**
     * Metodo que pasandole un name y dorsal, devuelve una instancia de
     * la clase Tortuga.
     *
     * @param name Nombre que se le asigna a la tortuga
     * @param dorsal Dorsal que se le asigna a la tortuga
     * @return Devuelve un objeto de tipo Turtle
     */
    public Turtle createTurtle(String name, int dorsal){
        System.out.println("Tortuga " + name + " y dorsal " + dorsal + " creada!!");
        return new Turtle(name, dorsal);
    }

    /**
     * Metodo que se encarga de añadir la tortuga al arraylist
     * @param turtle Objeto Turtle que se añade
     */
    public void addTurtle(Turtle turtle){
        System.out.println("Tortuga añadida a la lista");
        turtles.add(turtle);
    }

    /**
     * Elimina la tortuga de la lista pasandole como parametro el indice del arrayList
     * @param index Indice del arraylist que borrar
     * @return Devuelve el objeto a eliminar
     */
    public Turtle deleteTurtle(int index){
        return turtles.remove(index);
    }

    /**
     * Elimina la tortuga que corresponde a la tortuga pasada por parametro
     *
     * @param turtle Tortuga que queremos eliminar
     * @return Devuelve true si se ha borrado correctamente
     */
    public boolean deleteTurtle(Turtle turtle){
        return turtles.remove(turtle);
    }

    /**
     * Elimina todas las tortugas de la lista
     */
    public void deleteAllTurtles(){
        turtles.clear();
    }

    /**
     * Metodo que devuelve el indice en el arraylist en el que se encuentra el objeto TOrtuga
     *
     * @param turtle  Turtle que se desea buscar
     * @return Devuelve el indice
     */
    public int indexOf(Turtle turtle){
        return turtles.indexOf(turtle);
    }

    /**
     * Devuelve la tortuga cuyo indice coincida con el indice pasado por parametro
     * @param indice Indice pasado por parametro
     * @return Devuelve la Tortuga que corresponde a ese indice
     */
    public Turtle getTurtle(int indice){
        return turtles.get(indice);
    }

    /**
     * Metodo que se enccarga de modificar o sustituir una tortuga por otra.
     *
     * @param index Indice de la posicion de arraylist que se sustituira
     * @param editTurtle Objeto que sustituira al que se encuentra en la posicion index
     * @return Devuelve un objeto Turtle modificado
     */
    public Turtle setTurtle(int index, Turtle editTurtle){
        return turtles.set(index, editTurtle);
    }
}
