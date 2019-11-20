import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Clase Server.
 * Gestiona en envio y recepcion de mensajes con el cliente.
 *
 */
public class Server implements Observer {

    private boolean listening = true;
    private final int PORT = 1234;                  // Puerto del ServerSocket
    private ServerSocket serverSocket;              // Socket correspondiente al servidor
    private Socket socket;                          // Socket correspondiente al cliente
    private DataOutputStream messageToClient;       // Flujo de salida de datos
    private DataInputStream messageFromClient;      // Flujo de entrada de datos
    private ManageTurtle manageTurtle;              // Clase que gestiona las operaciones con tortugas
    private Thread[] hilos;                                 // Array que contiene los hilos

    public Server() throws IOException {
        hilos = new Thread[10];                     // Inicializamos el array en como maximo 10 corredores
        manageTurtle= new ManageTurtle();           // Creamos una instancia para gestionar las tortugas
        serverSocket = new ServerSocket(PORT);      // Creamos un server socket configurando el puerto
        socket = new Socket();                      // Creamos una instacia de un socket
    }
    // MENU
    /**
     * Metodo encargado de realizar la prueba de conexion con el cliente y de ejecutar el bucle que escucha
     * las peticiones del menu que envia el cliente
     * @throws IOException
     */
    public void initServer() throws IOException {
        try {
            connectClient();        // Prueba de conexion con el cliente

            while (listening) {     // Inicializamos el server para que escuche las peticiones en bucle
                try {
                    selector(receiveFromClient());     // Bucle infinito que espera la seleccion del menu en el cliente

                } catch (IndexOutOfBoundsException e) {
                    System.out.println(Constantes.ERROR_INVALID_VALUE);
                    System.out.println(Constantes.SEPARATOR);
                    sendToClient(Constantes.ERROR_INVALID_VALUE);
                }
            }
        }catch (Exception e){
            System.out.println("Error");
        }finally {
            serverSocket.close();
            System.out.println(Constantes.MESSAGE_DISCONNECT);
        }
    }

    /**
     * Recibe una entrada y en funcion de la misma, realiza una accion
     * @param input Entrada
     * @throws IOException
     */
    public void selector(String input) throws IOException {

        switch (input){
            case "1":
                System.out.println("\nNueva tortuga");
                newTurtle();
                break;
            case "2":
                System.out.println("\nEliminar tortuga");
                deleteTurtle();
                break;
            case "3":
                System.out.println("\nMostrar tortugas");
                showTurtleList();
                break;
            case "4":
                System.out.println("\nIniciar carrera");
                startRace();
                break;
            case "5":
                System.out.println("\nCerrando servidor...");
                listening = false;      // Cambiamos la variable listening para que pare el bucle
                break;
            default:
                System.out.println("\nError en la seleccion");
        }
    }


    //SERVER
    /**
     * Metodo encargado de hacer la prueba de conexion con el cliente
     * Envia y recibe el mensaje de prueba
     * En caso de error en la conexion lanza un IOException que propagaremos
     * @throws IOException
     */
    public void connectClient() throws IOException {

        System.out.println(Constantes.MESSAGE_WAITING_CLIENT);
        socket = serverSocket.accept();                     // Espera que detecte un cliente

        messageFromClient = new DataInputStream(socket.getInputStream());
        messageToClient = new DataOutputStream(socket.getOutputStream());

        sendToClient(Constantes.MESSAGE_CONNECTION);        // Enviamos mensaje al cliente
        System.out.println(receiveFromClient());            // Recibimos mensaje del cliente
    }

    /**
     * Metodo encargado de recibir mensajes del cliente
     * @return
     * @throws IOException
     */
    public String receiveFromClient() throws IOException {
        return  messageFromClient.readUTF();
    }

    /**
     * Envia mensajes al cliente
     * @param message
     * @throws IOException
     */
    public void sendToClient(String message) throws IOException {
        messageToClient.writeUTF(message);
    }


    // TORTUGAS
    /**
     * Intercambia informacion con el cliente para la creacion de una nueva tortuga
     * @throws IOException
     */
    public void newTurtle() throws IOException {

        String nombre = "";
        int dorsal = 0;

        sendToClient(Constantes.MESSAGE_TURTLE_NAME);
        nombre = receiveFromClient();
        sendToClient(Constantes.MESSAGE_TURTLE_DORSAL);
        dorsal = Integer.parseInt(receiveFromClient());

        // Creamos la torguga con el nombre y dorsal indicados y lo introducimos en el arraylist
        manageTurtle.addTurtle(manageTurtle.createTurtle(nombre, dorsal));

        sendToClient(Constantes.MESSAGE_NEW_TURTLE);
    }

    /**
     * Intercambia informacion con el cliente para la eliminacion de la tortuga indicada por el indice
     * @throws IOException
     */
    public void deleteTurtle() throws IOException {

        try {
            sendToClient(Constantes.MESSAGE_SELECT_TURTLE);
            int index = Integer.parseInt(receiveFromClient());
            Turtle turtle = manageTurtle.deleteTurtle(index -1);
            System.out.println(Constantes.MESSAGE_DELETE_TURTLE + turtle);
            sendToClient(Constantes.MESSAGE_DELETE_TURTLE + turtle);

        } catch (NumberFormatException e) {
            System.out.println("Error: valor invalido");
            sendToClient("Error: valor invalido");
        }
    }

    /**
     * Devuelve una cadena con la lista de tortugas
     * @throws IOException
     */
    public void showTurtleList() throws IOException {
        String turtleList = "";
        for(int i = 0; i < manageTurtle.getTurtles().size(); i++){
            turtleList += "Tortuga " + (i + 1) + ": " + manageTurtle.getTurtle(i) + "\n";
        }
        messageToClient.writeUTF(turtleList);
        manageTurtle.showAllTurtles();
    }

    /**
     * Inicializa la carrera
     * @throws IOException
     */
    public void startRace() {
        if(validateRace()){

            System.out.println("PUUUUUUMM!!! ");
            System.out.println("################ START RACE ###################");
            int i = 0;
            for (Turtle t: manageTurtle.getTurtles()){
                t.addObserver(this);
                hilos[i] = new Thread(t);
                hilos[i++].start();
            }
        }
    }

    /**
     * Comprueba que como maximo los participantes de la carrera sean 10
     * @return boolean
     */
    private boolean validateRace() {
        if (manageTurtle.getTurtles().size() > 10) {
            System.out.println("El máximo número de participantes es 10");
            return false;
        }else{
            return true;
        }
    }

    /**
     * Metodo sobreescrito de la interfaz observer que permite recoger el valor mandado del hilo
     * @param observable El objeto que lo manda Turtle
     * @param o El valor del name que llega antes. El ganador
     */
    @Override
    public void update(Observable observable, Object o) {
        // Recogemos el nombre del ganador y lo mandamos al cliente
        String name = (String)o;
        stopRace();
        try {
            sendToClient("###########################  THE WINNER IS: " + name + " ################################");
        } catch (IOException e) {
            System.out.println("Error al enviar el ganador al servidor");
        }
    }

    /**
     * Metodo que detiene el resto de hilos cuando recibe un valor
     */
    private void stopRace(){
        for (Thread t: hilos){
            if(t != null){
                t.interrupt();
            }
        }
    }
}
