import java.io.IOException;

/**
 * Clase que inicializa el servidor
 *
 * @author Jon Ander Ochoa
 */
public class MainServer {

    public static void main(String[] args){

        try {
            Server server = new Server();                   // Creamos la instancia de la clase servidor
            System.out.println("Iniciando servidor...");

            // Iniciamos el servidor
            server.initServer();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
