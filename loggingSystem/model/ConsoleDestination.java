package loggingSystem.model;

import loggingSystem.interfaces.Destination;

public class ConsoleDestination implements Destination {
    @Override
    public void printLogMessage(Message msg){
        System.out.println(msg.toString());
    }
}
