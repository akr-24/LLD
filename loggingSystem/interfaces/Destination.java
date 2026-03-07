package loggingSystem.interfaces;
import loggingSystem.model.*;

public interface Destination {
    public void printLogMessage(Message msg);
}