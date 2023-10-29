package client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.TimeUnit;
import exception.QueueFullException;
import server.MessagePool;

public class MessagePutClient {
	public static void main(String[] args) {
		try {
			// Locate the remote registry
			Registry registry = LocateRegistry.getRegistry(9000);

			// Do lookup inside the remote RMI registry
			MessagePool messagePool = (MessagePool) registry.lookup("messagePool");

			int counter = 1;
			while (true) {
				System.out.println("Sending message " + counter);
				messagePool.put("Message " + counter);

				counter++;
				TimeUnit.SECONDS.sleep(1);
			}
		}
		catch (QueueFullException e) {
			System.out.println("QueueFullException: " + e);
			e.printStackTrace();
		}
		catch (NotBoundException e) {
			System.out.println("NotBoundException: " + e);
			e.printStackTrace();
		}
		catch (InterruptedException e) {
			System.out.println("InterruptedException: " + e);
			e.printStackTrace();
		}
		catch (RemoteException e) {
			System.out.println("RemoteException: " + e);
			e.printStackTrace();
		}
	}
}
