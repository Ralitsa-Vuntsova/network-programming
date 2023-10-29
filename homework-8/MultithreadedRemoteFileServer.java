import java.io.*;
import java.net.*;

public class MultithreadedRemoteFileServer {
    protected int listenPort;

    public MultithreadedRemoteFileServer(int aListenPort) {
        listenPort = aListenPort;
    }

    public void acceptConnections() {
        try {
            ServerSocket server = new ServerSocket(listenPort, 5);

            Socket incomingConnection = null;
            while (true) {
                incomingConnection = server.accept();
                handleConnection(incomingConnection);
            }
        } catch (BindException e) {
            System.out.println("Unable to bind to port " + listenPort);
        } catch (IOException e) {
            System.out.println("Unable to instantiate a ServerSocket on port: " + listenPort);
        }
    }

    public void handleConnection(Socket connectionToHandle) {
        new Thread(new ConnectionHandler(connectionToHandle)).start();
    }

    public static void main(String[] args) {
        MultithreadedRemoteFileServer server = new MultithreadedRemoteFileServer(3000);
        server.acceptConnections();
    }
}
