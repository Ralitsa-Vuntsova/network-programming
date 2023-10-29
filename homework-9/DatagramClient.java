import java.io.IOException;
import java.net.* ;

public class DatagramClient {
    private final static int PACKETSIZE = 100;

    public static void main(String args[]) throws IOException {
        byte[] pingData = "iPING!".getBytes();

        InetAddress host = InetAddress.getLocalHost();
        int port = 1234;

        DatagramSocket socket = new DatagramSocket();

        DatagramPacket packet = new DatagramPacket(pingData, pingData.length, host, port);
        socket.send(packet);

        socket.setSoTimeout(10000);

        for (int i = 1; i < 5; i++) {
            try {
                packet.setData(new byte[PACKETSIZE]);
                socket.receive(packet);

                System.out.println("Pong package is received");
                break;
            } catch (SocketTimeoutException t) {
                packet.setData(pingData);
                socket.send(packet);
            }
        }

        socket.close();
    }
}
