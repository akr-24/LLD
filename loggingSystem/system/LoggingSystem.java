package loggingSystem.system;

import loggingSystem.enums.LogLevel;
import loggingSystem.model.Message;
import loggingSystem.interfaces.Destination;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class LoggingSystem {

    // volatile ensures changes to logLevel are visible across all threads
    private volatile LogLevel logLevel;
    private final List<Destination> destinations;

    // --- Singleton ---
    private static volatile LoggingSystem instance;

    private LoggingSystem(LogLevel logLevel){
        this.logLevel = logLevel;
        this.destinations = new CopyOnWriteArrayList<>();
    }

    // double-checked locking: safe and efficient for multi-threaded access
    public static LoggingSystem getInstance(LogLevel logLevel){
        if(instance == null){
            synchronized(LoggingSystem.class){
                if(instance == null){
                    instance = new LoggingSystem(logLevel);
                }
            }
        }
        return instance;
    }

    // --- API ---
    public void addDestination(Destination destination){
        destinations.add(destination);
    }

    public void setLogLevel(LogLevel logLevel){
        this.logLevel = logLevel;
    }

    public void printLogMessage(String content, LogLevel msgLogLevel){
        if(msgLogLevel.ordinal() < logLevel.ordinal()){
            return;
        }
        Message msg = new Message(msgLogLevel, content);

        for(Destination dst: destinations)
           dst.printLogMessage(msg);
    }

}
