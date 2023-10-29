import java.io.IOException;
import java.net.* ;

public class DatagramServer {
    private final static int PACKETSIZE = 100;

    public static void main(String args[]) throws IOException {
        String ping = "iPING!";
        String pong = "iPONG!";

        int port = 1234;

        DatagramSocket socket = new DatagramSocket(port);

        int counter = 0;
        for( ;; ) {
            DatagramPacket packet = new DatagramPacket(new byte[PACKETSIZE], PACKETSIZE);
            socket.receive(packet);

            String received = new String(packet.getData(), 0, packet.getLength());

            if (received.equals(ping)) {
                System.out.println("Ping package is received");

                packet.setData(pong.getBytes());
                socket.send(packet);
            }
            else {
                System.out.println("Other package is received");

                counter++;
                if (counter == 3) {
                    socket.close();
                    break;
                }
            }
        }
    }
}