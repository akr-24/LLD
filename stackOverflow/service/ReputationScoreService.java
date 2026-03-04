package stackOverflow.service;

import stackOverflow.enums.VoteType;
import stackOverflow.model.User;

public class ReputationScoreService {

    private static final int UPVOTE_REWARD   =  10;
    private static final int DOWNVOTE_PENALTY = -5;

    /*
     * Called after every vote action.
     * prevVote - the voter's previous vote (null if first time voting)
     * newVote  - the vote just cast
     */
    public void onVote(User contentOwner, VoteType prevVote, VoteType newVote) {
        int delta = 0;

        // Reverse the effect of the previous vote, if any
        if (prevVote == VoteType.UPVOTE)   delta -= UPVOTE_REWARD;
        if (prevVote == VoteType.DOWNVOTE) delta -= DOWNVOTE_PENALTY;

        // Apply the effect of the new vote
        if (newVote == VoteType.UPVOTE)   delta += UPVOTE_REWARD;
        if (newVote == VoteType.DOWNVOTE) delta += DOWNVOTE_PENALTY;

        contentOwner.applyReputationDelta(delta);
    }
}
