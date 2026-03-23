package notificationSystem.strategy;

import notificationSystem.model.Notification;

public class SmsChannel implements Channel {

    @Override
    public void sendNotification(Notification notification) {
        System.out.println("[SMS]"
                + " To: "        + notification.getRecipient()
                + " | SenderId: " + notification.getSenderId()
                + " | Message: "  + notification.getMessage());
    }
}
