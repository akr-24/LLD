package notificationSystem.exception;

public class MissingFieldException extends RuntimeException {
    public MissingFieldException(String fieldName, String channel) {
        super("'" + fieldName + "' is mandatory for " + channel + " notifications");
    }
}
