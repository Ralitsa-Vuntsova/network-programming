import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RMIServer {
    public static void main(String[] args) {
        try{
            System.out.println("Server is booting up!");

            // Create an object from WordCounterImpl class
            WordCounterImpl wordCounter = new WordCounterImpl();
            // Exported object is called stub
            WordCounter stub = (WordCounter) UnicastRemoteObject.exportObject(wordCounter, 0);

            // We will run our RMI registry on port 9000
            Registry registry = LocateRegistry.createRegistry(9001);
            // Using registry.bind to register the object to RMI registry.
            registry.bind("wordCounter", stub);

            System.out.println("Server ready!");
        }
        catch (AlreadyBoundException e) {
            e.printStackTrace();
        }
        catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
