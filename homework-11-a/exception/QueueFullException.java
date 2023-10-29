package exception;

import java.rmi.RemoteException;

/**
 * In case the queue is full, the "put()" operation will fail,
 * and a QueueFullException will be thrown.
 */
public class QueueFullException extends RemoteException {

	public QueueFullException(String msg) {
		super(msg);
	}
}
