package loggingSystem.destination;

import loggingSystem.model.Message;
import java.io.FileWriter;

public class FileDestination implements Destination {

    private String filePath;

    public FileDestination(String filePath){
        this.filePath = filePath;
    }

    @Override
    public void printLogMessage(Message msg){
        // appending at the end of the file insteading to erasing the existing content

        // try with resources
        try(FileWriter fw = new FileWriter(filePath, true)){
            fw.write(msg.toString() + "\n");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
