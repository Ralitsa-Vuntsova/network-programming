import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.concurrent.TimeUnit;

public class MulticastPublisher {
    public static void main(String[] args) throws IOException, InterruptedException {
        DatagramSocket socket = new DatagramSocket();
        InetAddress group = InetAddress.getByName("230.0.0.0");

        File f = new File("src", "the-perks-of-being-a-wallflower.txt");
        BufferedReader br = new BufferedReader(new FileReader(f));

        String line = br.readLine();
        while (line != null) {
            byte[] buffer = line.getBytes();

            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, group, 4446);
            socket.send(packet);

            TimeUnit.SECONDS.sleep(2);

            line = br.readLine();
        }

        socket.close();
    }
}