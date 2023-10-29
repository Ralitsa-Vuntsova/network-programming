package client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.TimeUnit;

import server.MessagePool;
import exception.QueueEmptyException;

public class MessageGetClient {
	public static void main(String[] args) {
		try {
			// Locate the remote registry
			Registry registry = LocateRegistry.getRegistry(9000);

			// Do lookup inside the remote RMI registry
			MessagePool messagePool = (MessagePool) registry.lookup("messagePool");

			while(true) {
				TimeUnit.SECONDS.sleep(2);

				String message = messagePool.get();
				System.out.println("MessageGetClient received message: " + message);
			}
		}
		catch (QueueEmptyException e) {
			System.out.println("QueueEmptyException: " + e);
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
