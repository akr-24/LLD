package loggingSystem.factory;

import loggingSystem.destination.ConsoleDestination;
import loggingSystem.destination.DatabaseDestination;
import loggingSystem.destination.FileDestination;
import loggingSystem.destination.Destination;
public class DestinationFactory {

   public static Destination createDestination(String destination){
        if("Console".equals(destination)){
            return new ConsoleDestination();
        }
        // else if("Database".equals(destination)){
        //     return new DatabaseDestination(dataSource); // inject a real DataSource
        // }
        else if("File".equals(destination)){
            return new FileDestination("file.txt");
        }
        else{
            throw new IllegalArgumentException("Unknown destination type:" + destination);
        }
    }
}
