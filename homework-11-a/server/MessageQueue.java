package server;

import exception.QueueEmptyException;
import exception.QueueFullException;

import java.util.LinkedList;

public class MessageQueue {
    private LinkedList<Message> messages;

    public MessageQueue() {
        messages = new LinkedList<>();
    }

    public void put(Message message) throws QueueFullException {
        if(messages.size() == 100) {
            throw new QueueFullException("Message queue is full!");
        }

        messages.addLast(message);
    }

    public Message get() throws QueueEmptyException {
        if(messages.isEmpty()) {
            throw new QueueEmptyException("Message queue is empty!");
        }

        Message first = messages.getFirst();
        messages.removeFirst();

        return first;
    }
}
