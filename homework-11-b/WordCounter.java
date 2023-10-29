import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

public interface WordCounter extends Remote {
    Map<String, Integer> countWords(String line) throws RemoteException;
}
