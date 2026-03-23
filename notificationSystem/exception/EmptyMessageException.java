package notificationSystem.exception;

public class EmptyMessageException extends RuntimeException {
    public EmptyMessageException() {
        super("message cannot be empty");
    }
}
