import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    private List<Tortuga> tortugas;
    private final int PUERTO = 1234;        // Socket correspondiente al servidor
    private ServerSocket serverSocket;      // SOcket correspondiente al cliente
    private Socket socket;

    // Constructor
    public Server() throws IOException {
        tortugas = new ArrayList<Tortuga>();
        serverSocket = new ServerSocket(PUERTO);
        socket = new Socket();
    }

    /*
    Tenemos que recibir los mensajes del cliente y en funcion del tipo de
    mensaje:
    - mostraremos las tortugas
    - crearemos una nueva tortuga
    - Eliminaremos una tortuga
    - Iniciaremos la carrera
     */
    public void iniciarServer() throws IOException {
        DataOutputStream mensajeCliente;
        String mensajeDeCliente;
        try{
            // Inicializamos el server para que escuche las peticiones en bucle
            while(true){
                System.out.println("Esperando la conexion del cliente");
                socket = serverSocket.accept();

                // Enviamos mensaje al cliente para decirle que hemos recibido su peticion
                mensajeCliente = new DataOutputStream(socket.getOutputStream());
                mensajeCliente.writeUTF("Peticion recibida");

                // Recibimos los mensajes que nos mande el cliente
                BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                while((mensajeDeCliente = entrada.readLine()) != null){
                    System.out.println(mensajeDeCliente);
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            serverSocket.close();
            System.out.println("Fin de la conexion");
        }
    }

    /**
     * Muestra todas las tortugas que contiene el arrayList
     */
    public void mostrarTortugas(){
        for (Tortuga t: tortugas){
            System.out.println(t);
        }
    }

    /**
     * Metodo que pasandole un nombre y dorsal, devuelve una instancia de
     * la clase Tortuga.
     *
     * @param nombre Nombre que se le asigna a la tortuga
     * @param dorsal Dorsal que se le asigna a la tortuga
     * @return Devuelve un objeto de tipo Tortuga
     */
    public Tortuga crearTortuga(String nombre, int dorsal){
        return new Tortuga(nombre, dorsal);
    }

    /**
     * Metodo que se encarga de añadir la tortuga al arraylist
     * @param tortuga Objeto Tortuga que se añade
     */
    public void addTortuga(Tortuga tortuga){
        tortugas.add(tortuga);
    }

    /**
     * Elimina la tortuga de la lista pasandole como parametro el indice del arrayList
     * @param index Indice del arraylist que borrar
     * @return Devuelve el objeto a eliminar
     */
    public Tortuga eliminarTortuga(int index){
        return tortugas.remove(index);
    }

    /**
     * Elimina la tortuga que corresponde a la tortuga pasada por parametro
     *
     * @param tortuga Tortuga que queremos eliminar
     * @return Devuelve true si se ha borrado correctamente
     */
    public boolean eliminarTortuga(Tortuga tortuga){
        return tortugas.remove(tortuga);
    }

    /**
     * Elimina todas las tortugas de la lista
     */
    public void eliminaTodasTortugas(){
        tortugas.clear();
    }

    public void empezarCarrera(){
        System.out.println("Empezar la carrera...");
    }

    /**
     * Metodo que devuelve el indice en el arraylist en el que se encuentra el objeto TOrtuga
     *
     * @param tortuga  Tortuga que se desea buscar
     * @return Devuelve el indice
     */
    public int indexOf(Tortuga tortuga){
        return tortugas.indexOf(tortuga);
    }

    /**
     * Devuelve la tortuga cuyo indice coincida con el indice pasado por parametro
     * @param indice Indice pasado por parametro
     * @return Devuelve la Tortuga que corresponde a ese indice
     */
    public Tortuga getTortuga(int indice){
        return tortugas.get(indice);
    }

    /**
     * Metodo que se enccarga de modificar o sustituir una tortuga por otra.
     *
     * @param index Indice de la posicion de arraylist que se sustituira
     * @param tortugaModificada Objeto que sustituira al que se encuentra en la posicion index
     * @return Devuelve un objeto Tortuga modificado
     */
    public Tortuga setTortugas(int index, Tortuga tortugaModificada){
        return tortugas.set(index, tortugaModificada);
    }
}
