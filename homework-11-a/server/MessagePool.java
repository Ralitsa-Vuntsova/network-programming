package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MessagePool extends Remote {
	void put(String msg) throws RemoteException;

	String get() throws RemoteException;
}
