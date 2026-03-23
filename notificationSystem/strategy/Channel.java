package notificationSystem.strategy;
import notificationSystem.model.*;
public interface Channel {
    void sendNotification(Notification notification);
}
