package loggingSystem.destination;

import loggingSystem.model.Message;

public interface Destination {
    public void printLogMessage(Message msg);
}
