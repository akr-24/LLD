package loggingSystem;
import loggingSystem.system.LoggingSystem;
import loggingSystem.enums.LogLevel;
import loggingSystem.factory.DestinationFactory;

public class Main {
    public static void main(String[] args){
        LoggingSystem logSystem = LoggingSystem.getInstance(LogLevel.INFO);
        logSystem.addDestination(DestinationFactory.createDestination("File"));
        logSystem.addDestination(DestinationFactory.createDestination("Console"));

        logSystem.printLogMessage("This is my first log", LogLevel.DEBUG);
        logSystem.printLogMessage("This is second error msg", LogLevel.ERROR);
    }
}
