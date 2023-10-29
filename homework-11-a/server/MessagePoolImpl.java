package server;

import exception.QueueEmptyException;
import exception.QueueFullException;

public class MessagePoolImpl implements MessagePool {
	private MessageQueue messageQueue;

	public MessagePoolImpl() {
		messageQueue = new MessageQueue();
	}

	public void put(String msg) throws QueueFullException {
		Message message = new Message(msg);
		messageQueue.put(message);
	}
	
	public String get() throws QueueEmptyException {
		return messageQueue.get().getMessage();
	}
}
