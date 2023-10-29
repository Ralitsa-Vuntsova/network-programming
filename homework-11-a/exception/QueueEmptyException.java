package exception;

import java.rmi.RemoteException;

/**
 * In case the queue is empty, the "get()" operation will fail 
 * and a QueueEmptyException exception will be thrown.
 */
public class QueueEmptyException extends RemoteException{

	public QueueEmptyException(String msg) {
		super(msg);
	}
}
