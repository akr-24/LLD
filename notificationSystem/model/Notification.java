package notificationSystem.model;

import notificationSystem.enums.ChannelType;
import notificationSystem.enums.Priority;
import notificationSystem.exception.EmptyMessageException;
import notificationSystem.exception.InvalidRetryCountException;
import notificationSystem.exception.MissingFieldException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Notification {

    // ── Mandatory fields ──────────────────────────────────────────────────────
    private final String recipient;
    private final String message;
    private final ChannelType channel;

    // ── Common optional fields ────────────────────────────────────────────────
    private final Priority priority;
    private final LocalDateTime timestamp;
    private final int retryCount;

    // ── Email-specific optional ───────────────────────────────────────────────
    private final String subject;
    private final List<String> cc;
    private final List<String> bcc;
    private final List<String> attachments;

    // ── SMS-specific optional ─────────────────────────────────────────────────
    private final String senderId;

    // ── Push-specific optional ────────────────────────────────────────────────
    private final String imageUrl;
    private final String actionUrl;

    // Only the inner Builder can call this
    private Notification(Builder builder) {
        this.recipient   = builder.recipient;
        this.message     = builder.message;
        this.channel     = builder.channel;
        this.priority    = builder.priority;
        this.timestamp   = builder.timestamp;
        this.retryCount  = builder.retryCount;
        this.subject     = builder.subject;
        this.cc          = Collections.unmodifiableList(new ArrayList<>(builder.cc));
        this.bcc         = Collections.unmodifiableList(new ArrayList<>(builder.bcc));
        this.attachments = Collections.unmodifiableList(new ArrayList<>(builder.attachments));
        this.senderId    = builder.senderId;
        this.imageUrl    = builder.imageUrl;
        this.actionUrl   = builder.actionUrl;
    }

    // ── Getters only — no setters ─────────────────────────────────────────────
    public String getRecipient()         { return recipient; }
    public String getMessage()           { return message; }
    public ChannelType getChannel()      { return channel; }
    public Priority getPriority()        { return priority; }
    public LocalDateTime getTimestamp()  { return timestamp; }
    public int getRetryCount()           { return retryCount; }
    public String getSubject()           { return subject; }
    public List<String> getCc()          { return cc; }
    public List<String> getBcc()         { return bcc; }
    public List<String> getAttachments() { return attachments; }
    public String getSenderId()          { return senderId; }
    public String getImageUrl()          { return imageUrl; }
    public String getActionUrl()         { return actionUrl; }

    @Override
    public String toString() {
        return "Notification{"
                + "recipient='" + recipient + '\''
                + ", channel=" + channel
                + ", priority=" + priority
                + ", message='" + message + '\''
                + ", subject='" + subject + '\''
                + ", senderId='" + senderId + '\''
                + ", retryCount=" + retryCount
                + '}';
    }

    // ═════════════════════════════════════════════════════════════════════════
    //  Static inner Builder
    // ═════════════════════════════════════════════════════════════════════════
    public static class Builder {

        // Mandatory — enforced via constructor, no way to skip
        private final String recipient;
        private final String message;
        private final ChannelType channel;

        // Common optional — sensible defaults
        private Priority priority       = Priority.MEDIUM;
        private LocalDateTime timestamp = LocalDateTime.now();
        private int retryCount          = 0;

        // Email-specific
        private String subject;
        private List<String> cc          = new ArrayList<>();
        private List<String> bcc         = new ArrayList<>();
        private List<String> attachments = new ArrayList<>();

        // SMS-specific
        private String senderId;

        // Push-specific
        private String imageUrl;
        private String actionUrl;

        public Builder(String recipient, String message, ChannelType channel) {
            this.recipient = recipient;
            this.message   = message;
            this.channel   = channel;
        }

        // ── Fluent setters (each returns 'this' for chaining) ─────────────────

        public Builder priority(Priority priority) {
            this.priority = priority;
            return this;
        }

        public Builder timestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder retryCount(int retryCount) {
            this.retryCount = retryCount;
            return this;
        }

        public Builder subject(String subject) {
            this.subject = subject;
            return this;
        }

        public Builder cc(List<String> cc) {
            this.cc = cc;
            return this;
        }

        public Builder bcc(List<String> bcc) {
            this.bcc = bcc;
            return this;
        }

        public Builder attachments(List<String> attachments) {
            this.attachments = attachments;
            return this;
        }

        public Builder senderId(String senderId) {
            this.senderId = senderId;
            return this;
        }

        public Builder imageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public Builder actionUrl(String actionUrl) {
            this.actionUrl = actionUrl;
            return this;
        }

        // ── Validation + build ────────────────────────────────────────────────

        private void validate() {
            if (message == null || message.isBlank()) {
                throw new EmptyMessageException();
            }
            if (channel == ChannelType.EMAIL && (subject == null || subject.isBlank())) {
                throw new MissingFieldException("subject", "EMAIL");
            }
            if (channel == ChannelType.SMS && (senderId == null || senderId.isBlank())) {
                throw new MissingFieldException("senderId", "SMS");
            }
            if (retryCount > 5) {
                throw new InvalidRetryCountException(retryCount);
            }
        }

        public Notification build() {
            validate();
            return new Notification(this);
        }
    }
}
