package loggingSystem.model;

import java.time.Instant;
import loggingSystem.enums.*;

public class Message {
    
    private Instant timeStamp;
    private LogLevel  logLevel;
    private String content;

    public Message(LogLevel logLevel, String content){
        this.timeStamp = Instant.now();
        this.logLevel = logLevel;
        this.content = content;
    }

    public String getContent(){
        return this.content;
    }

    public LogLevel getLogLevel(){
        return this.logLevel;
    }

    public Instant getTimeStamp(){
        return this.timeStamp;
    }

    @Override
    public String toString(){
        return timeStamp.toString() + " " + logLevel.name() + " " + content;
    }

}
