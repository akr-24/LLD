package notificationSystem.strategy;

import notificationSystem.model.Notification;

public class EmailChannel implements Channel {

    @Override
    public void sendNotification(Notification notification) {
        System.out.println("[EMAIL]"
                + " To: "       + notification.getRecipient()
                + " | Subject: " + notification.getSubject()
                + " | Priority: " + notification.getPriority()
                + " | Message: " + notification.getMessage());
    }
}
