package stackOverflow.model;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import stackOverflow.enums.VoteType;
import stackOverflow.interfaces.Commentable;
import stackOverflow.interfaces.Votable;

public class Question implements Commentable, Votable {
    private final String questionId;
    private String questionContent;
    private final List<Answer> answers = new CopyOnWriteArrayList<>();
    private final List<Comment> comments = new CopyOnWriteArrayList<>();
    private final List<Tag> tags = new CopyOnWriteArrayList<>();
    private final Map<String, Vote> votesByUserId = new ConcurrentHashMap<>();

    private User user;
    private int voteCount;

    public Question(String content, User user) {
        this(content, user, null);
    }

    public Question(String content, User user, List<Tag> initialTags) {
        this.questionContent = content;
        this.questionId = UUID.randomUUID().toString();
        this.user = user;
        if (initialTags != null) {
            this.tags.addAll(initialTags);
        }
    }

    public void addAnswer(Answer answer) {
        answers.add(answer);
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public synchronized Vote addOrUpdateVote(User voter, VoteType type) {
        String voterId = voter.getUserId();
        Vote existing = votesByUserId.get(voterId);
        if (existing == null) {
            Vote vote = new Vote(voter, type);
            votesByUserId.put(voterId, vote);
            recomputeVoteCount();
            return vote;
        } else {
            existing.setType(type);
            recomputeVoteCount();
            return existing;
        }
    }

    private void recomputeVoteCount() {
        int count = 0;
        for (Vote vote : votesByUserId.values()) {
            if (vote.getType() == VoteType.UPVOTE) {
                count++;
            } else if (vote.getType() == VoteType.DOWNVOTE) {
                count--;
            }
        }
        this.voteCount = count;
    }

    @Override
    public VoteType getCurrentVote(User voter) {
        Vote vote = votesByUserId.get(voter.getUserId());
        return (vote == null) ? null : vote.getType();
    }

    @Override
    public User getOwner() {
        return user;
    }

    public void addTag(Tag tag) {
        tags.add(tag);
    }

    public String getQuestionId() {
        return questionId;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public User getUser() {
        return user;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public List<Answer> getAnswers() {
        return Collections.unmodifiableList(answers);
    }

    public List<Comment> getComments() {
        return Collections.unmodifiableList(comments);
    }

    public List<Tag> getTags() {
        return Collections.unmodifiableList(tags);
    }
}
