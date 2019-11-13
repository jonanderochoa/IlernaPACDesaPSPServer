import java.io.IOException;

/**
 * @author Jon Ander Ochoa
 */
public class MainServer {

    public static void main(String[] args) throws IOException {

        // Creamos instancia de la clase servidor
        Server server = new Server();

        System.out.println("Iniciando servidor...");

        // Iniciamos el servidor
        server.iniciarServer();
    }
}
