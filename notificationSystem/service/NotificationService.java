package notificationSystem.service;

import notificationSystem.enums.ChannelType;
import notificationSystem.exception.UnsupportedChannelException;
import notificationSystem.model.Notification;
import notificationSystem.strategy.Channel;
import notificationSystem.strategy.EmailChannel;
import notificationSystem.strategy.PushChannel;
import notificationSystem.strategy.SmsChannel;

import java.util.HashMap;
import java.util.Map;

public class NotificationService {

    private final Map<ChannelType, Channel> channelRegistry;

    public NotificationService() {
        channelRegistry = new HashMap<>();
        channelRegistry.put(ChannelType.EMAIL, new EmailChannel());
        channelRegistry.put(ChannelType.SMS,   new SmsChannel());
        channelRegistry.put(ChannelType.PUSH,  new PushChannel());
    }

    public void send(Notification notification) {
        Channel channel = channelRegistry.get(notification.getChannel());
        if (channel == null) {
            throw new UnsupportedChannelException(notification.getChannel());
        }
        channel.sendNotification(notification);
    }
}
