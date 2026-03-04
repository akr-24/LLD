package stackOverflow.model;

import stackOverflow.enums.VoteType;
import stackOverflow.interfaces.Commentable;
import stackOverflow.interfaces.Votable;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Answer implements Commentable, Votable {

    private final String answerId;
    private User user;
    private String content;
    private final List<Comment> comments = new CopyOnWriteArrayList<>();
    private final Map<String, Vote> votesByUserId = new ConcurrentHashMap<>();
    private int voteCount;

    public Answer(User user, String content){
        this.answerId = UUID.randomUUID().toString();
        this.user = user;
        this.content = content;
    }

    public void addComment(Comment comment){
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

    public String getAnswerId() {
        return answerId;
    }

    public User getUser() {
        return user;
    }

    public String getContent() {
        return content;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public List<Comment> getComments() {
        return Collections.unmodifiableList(comments);
    }
}
