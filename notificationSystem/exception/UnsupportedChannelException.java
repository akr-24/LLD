package notificationSystem.exception;

import notificationSystem.enums.ChannelType;

public class UnsupportedChannelException extends RuntimeException {
    public UnsupportedChannelException(ChannelType channelType) {
        super("No channel implementation found for: " + channelType);
    }
}
