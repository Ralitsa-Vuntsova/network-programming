package server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class MessagePoolServer {
	public static void main(String[] args) {
		try{
			System.out.println("Server is booting up!");

			// Create an object from MessagePoolImpl class
			MessagePoolImpl messagePool = new MessagePoolImpl();
			// Exported object is called stub
			MessagePool stub = (MessagePool) UnicastRemoteObject.exportObject(messagePool, 0);

			// We will run our RMI registry on port 9000
			Registry registry = LocateRegistry.createRegistry(9000);
			// Using registry.bind to register the object to RMI registry.
			registry.bind("messagePool", stub);

			System.out.println("Server ready!");
		}
		catch (AlreadyBoundException e) {
			System.out.println("AlreadyBoundException: " + e);
			e.printStackTrace();
		}
		catch (RemoteException e) {
			System.out.println("RemoteException: " + e);
			e.printStackTrace();
		}
	}
}
