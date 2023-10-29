import java.io.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Map;

public class RMIClient {
    public static void main(String[] args) {
        try {
            // Locate the remote registry
            Registry registry = LocateRegistry.getRegistry(9001);

            // Do lookup inside the remote RMI registry
            WordCounter wordCounter = (WordCounter) registry.lookup("wordCounter");

            File f = new File("src", "lorem-ipsum.txt");
            BufferedReader br = new BufferedReader(new FileReader(f));

            String line = br.readLine();
            while (line != null) {
                byte[] buffer = line.getBytes();
                String lineToString = new String(buffer);
                Map<String, Integer> result = wordCounter.countWords(lineToString);

                System.out.println("Line:");
                System.out.println(lineToString);

                System.out.println("Result:");
                for (String word : result.keySet()) {
                    System.out.print(word + ": " +result.get(word) + "   ");
                }
                System.out.println();

                line = br.readLine();
            }
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
