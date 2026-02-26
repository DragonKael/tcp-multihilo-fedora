import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread extends Thread {
    private final Socket socket;
    private final int clientId;

    public ServerThread(Socket socket, int clientId) {
        this.socket = socket;
        this.clientId = clientId;
    }

    @Override
    public void run() {
        try (
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            out.println("¡Bienvenido! ¿Cuál es tu nombre?");
            String nombre = in.readLine();
            System.out.println("Client says: " + nombre);

            String respuesta = "Hola " + nombre + "!";
            // Si es la segunda conexión, agregar mensaje extra
            if (clientId == 2) {
                respuesta += " tuviste suerte";
            }

            out.println(respuesta);
            socket.close();
            System.out.println("Client " + clientId + ". has disconnected.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
