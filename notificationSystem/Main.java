package notificationSystem;

import notificationSystem.enums.ChannelType;
import notificationSystem.enums.Priority;
import notificationSystem.model.Notification;
import notificationSystem.service.NotificationService;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        NotificationService service = new NotificationService();

        // ── 1. Email notification ─────────────────────────────────────────────
        Notification emailNotification = new Notification.Builder(
                "user@example.com",
                "Your order has been placed!",
                ChannelType.EMAIL)
                .subject("Order Confirmation")
                .priority(Priority.HIGH)
                .cc(List.of("support@example.com"))
                .retryCount(2)
                .build();

        service.send(emailNotification);

        // ── 2. SMS notification ───────────────────────────────────────────────
        Notification smsNotification = new Notification.Builder(
                "+91-9876543210",
                "Your OTP is 4521",
                ChannelType.SMS)
                .senderId("MYAPP")
                .priority(Priority.HIGH)
                .build();

        service.send(smsNotification);

        // ── 3. Push notification ──────────────────────────────────────────────
        Notification pushNotification = new Notification.Builder(
                "device-token-xyz",
                "Flash sale! 50% off for next 2 hours",
                ChannelType.PUSH)
                .imageUrl("https://cdn.myapp.com/sale-banner.png")
                .actionUrl("https://myapp.com/sale")
                .priority(Priority.MEDIUM)
                .build();

        service.send(pushNotification);

        // ── 4. Validation failure — EMAIL without subject ─────────────────────
        System.out.println("\n-- Attempting EMAIL without subject --");
        try {
            Notification invalid = new Notification.Builder(
                    "user@example.com",
                    "Missing subject email",
                    ChannelType.EMAIL)
                    .build();
            service.send(invalid);
        } catch (RuntimeException e) {
            System.out.println("Caught expected error: " + e.getMessage());
        }
    }
}
