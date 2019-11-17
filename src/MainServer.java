import java.io.IOException;

/**
 * @author Jon Ander Ochoa
 */
public class MainServer {

    public static void main(String[] args){

        try {
            // Creamos instancia de la clase servidor
            Server server = new Server();

            System.out.println("Iniciando servidor...");

            // Iniciamos el servidor
            server.initServer();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
