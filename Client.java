import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        // CAMBIA ESTA IP POR LA DIRECCIÓN REAL DE LA VM SERVIDOR
        String serverIP = "192.168.1.100";
        int serverPort = 2020;

        try (
            Socket socket = new Socket(serverIP, serverPort);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))
        ) {
            String mensajeServidor = in.readLine();
            System.out.println("Servidor dice: " + mensajeServidor);

            System.out.print("Ingresa tu nombre: ");
            String nombre = userInput.readLine();
            out.println(nombre);

            String respuesta = in.readLine();
            System.out.println("Servidor dice: " + respuesta);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Socket closed.");
    }
}
