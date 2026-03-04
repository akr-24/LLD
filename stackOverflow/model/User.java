package stackOverflow.model;

import java.util.Collections;
import java.util.List;

import stackOverflow.interfaces.Commentable;

public class User {
    private final String userId;
    private int reputationScore;

    public User(String userId) {
        this.userId = userId;
        this.reputationScore = 0;
    }

    public Question postQuestion(String content) {
        return new Question(content, this);
    }

    public Question postQuestion(String content, List<Tag> tags) {
        List<Tag> safeTags = (tags == null) ? Collections.emptyList() : tags;
        return new Question(content, this, safeTags);
    }

    public Comment comment(String content, Commentable target) {
        Comment comment = new Comment(content, this);
        target.addComment(comment);
        return comment;
    }

    public Answer postAnswer(String content, Question question) {
        Answer ans = new Answer(this, content);
        question.addAnswer(ans);
        return ans;
    }

    public int getReputationScore() {
        return this.reputationScore;
    }

    public String getUserId() {
        return this.userId;
    }

    public void applyReputationDelta(int delta) {
        this.reputationScore += delta;
    }
}
