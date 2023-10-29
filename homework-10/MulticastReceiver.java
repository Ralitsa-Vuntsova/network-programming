import java.io.IOException;
import java.net.*;

public class MulticastReceiver extends Thread {
    protected MulticastSocket socket = null;
    protected byte[] buffer = new byte[256];
    int port = 4446;

    public void run() {
        try {
            socket = new MulticastSocket(port);

            InetAddress multicastAddress = InetAddress.getByName("230.0.0.0");
            InetSocketAddress group = new InetSocketAddress(multicastAddress, port);
            NetworkInterface netIf = NetworkInterface.getByName("bge0");

            socket.joinGroup(group, netIf);

            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                String received = new String(packet.getData(), 0, packet.getLength());
                System.out.println(received);

                if ("The end".equals(received)) {
                    break;
                }
            }

            socket.leaveGroup(group, netIf);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MulticastReceiver firstReceiver = new MulticastReceiver();
        firstReceiver.start();

        MulticastReceiver secondReceiver = new MulticastReceiver();
        secondReceiver.start();

        MulticastReceiver thirdReceiver = new MulticastReceiver();
        thirdReceiver.start();
    }
}