import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
    private static int clientCounter = 0;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(2020)) {
            System.out.println("Port 2020 is now open.");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                clientCounter++;
                int clientId = clientCounter;

                System.out.println("Client " + clientId + ". has connected.");

                ServerThread thread = new ServerThread(clientSocket, clientId);
                thread.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
