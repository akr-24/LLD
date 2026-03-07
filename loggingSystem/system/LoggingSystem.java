package loggingSystem.system;

import loggingSystem.enums.LogLevel;
import loggingSystem.model.Message;
import loggingSystem.interfaces.Destination;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;

public class LoggingSystem {

    private volatile LogLevel logLevel;
    private final List<Destination> destinations;
    private final BlockingQueue<Message> logQueue;

    // --- Singleton ---
    private static volatile LoggingSystem instance;

    private LoggingSystem(LogLevel logLevel){
        this.logLevel = logLevel;
        this.destinations = new CopyOnWriteArrayList<>();
        this.logQueue     = new LinkedBlockingQueue<>(1000);
        startWorker();
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
        logQueue.offer(msg);  // non-blocking enqueue; drops if queue is full
    }

    private void startWorker(){
        Thread worker = new Thread(() -> {
            while(!Thread.currentThread().isInterrupted()){
                try {
                    Message msg = logQueue.take();  // waits if empty
                    for(Destination d : destinations)
                        d.printLogMessage(msg);
                } catch(InterruptedException e){
                    Thread.currentThread().interrupt();  // restore flag and exit loop
                }
            }
        });
        worker.setDaemon(true);
        worker.start();
    }

}
