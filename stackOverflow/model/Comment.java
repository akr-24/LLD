package stackOverflow.model;

import java.util.UUID;

public class Comment {
    private final String commentId;
    private String content;
    private User user;

    public Comment(String content, User user) {
        this.commentId = UUID.randomUUID().toString();
        this.user = user;
        this.content = content;
    }

    public String getCommentId() {
        return commentId;
    }

    public String getContent() {
        return content;
    }

    public User getUser() {
        return user;
    }
}
