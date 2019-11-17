import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private final int PORT = 1234;
    private ServerSocket serverSocket;              // Socket correspondiente al servidor
    private Socket socket;                          // Socket correspondiente al cliente
    private DataOutputStream messageToClient;
    private DataInputStream messageFromClient;
    private ManageTurtle manageTurtle;

    public Server() throws IOException {
        manageTurtle= new ManageTurtle();
        // Creamos un server socket configurando el puerto
        serverSocket = new ServerSocket(PORT);
        socket = new Socket();
    }
    
    public void initServer() throws IOException {
        try {
            connectClient();

            // Inicializamos el server para que escuche las peticiones en bucle
            while (true) {

                try {
//                socket = serverSocket.accept();
                    selector(receiveFromClient());
//                socket.close();
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(Constantes.ERROR_INVALID_VALUE);
                    System.out.println(Constantes.SEPARATOR);
                    sendToClient(Constantes.ERROR_INVALID_VALUE);
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            serverSocket.close();
            System.out.println(Constantes.MESSAGE_DISCONNECT);
        }
    }

    public void connectClient() throws IOException {

        // Conexion con en cliente
        System.out.println(Constantes.MESSAGE_WAITING_CLIENT);
        socket = serverSocket.accept();

        messageFromClient = new DataInputStream(socket.getInputStream());
        messageToClient = new DataOutputStream(socket.getOutputStream());

        // Enviamos mensaje al cliente
        sendToClient(Constantes.MESSAGE_CONNECTION);

        // Recibimos mensaje del cliente
        System.out.println(receiveFromClient());
//        socket.close();
    }

    public String receiveFromClient() throws IOException {
        return  messageFromClient.readUTF();
    }

    public void sendToClient(String message) throws IOException {
        messageToClient.writeUTF(message);
    }

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
//                manageTurtle.showAllTurtles();
                break;
            case "4":
                System.out.println("\nIniciar carrera");
                startRace();
                break;
            default:
                System.out.println("\nError en la seleccion");
        }
    }

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

    public void deleteTurtle() throws IOException {

        try {
            int index = 0;

            sendToClient(Constantes.MESSAGE_SELECT_TURTLE);
            index = Integer.parseInt(receiveFromClient());
            Turtle turtle = manageTurtle.deleteTurtle(index -1);
            System.out.println(Constantes.MESSAGE_DELETE_TURTLE + turtle);
            sendToClient(Constantes.MESSAGE_DELETE_TURTLE + turtle);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public void showTurtleList() throws IOException {
//        for(int i = 0; i < manageTurtle.getTurtles().size(); i++){
//            messageToClient.writeUTF("Tortuga " + (i + 1) + manageTurtle.getTurtle(i));
//        }
        manageTurtle.showAllTurtles();
    }

    public void startRace() throws IOException {
        System.out.println("A correr todo el mundo");
    }
}
