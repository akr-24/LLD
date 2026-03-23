package notificationSystem.exception;

public class InvalidRetryCountException extends RuntimeException {
    public InvalidRetryCountException(int given) {
        super("retryCount cannot exceed 5, given: " + given);
    }
}
