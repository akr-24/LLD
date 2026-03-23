package notificationSystem.strategy;

import notificationSystem.model.Notification;

public class PushChannel implements Channel {

    @Override
    public void sendNotification(Notification notification) {
        System.out.println("[PUSH]"
                + " To: "          + notification.getRecipient()
                + " | Message: "   + notification.getMessage()
                + " | ImageUrl: "  + notification.getImageUrl()
                + " | ActionUrl: " + notification.getActionUrl());
    }
}
