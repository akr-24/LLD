package loggingSystem.destination;

import loggingSystem.model.Message;

public class ConsoleDestination implements Destination {
    @Override
    public void printLogMessage(Message msg){
        System.out.println(msg.toString());
    }
}
