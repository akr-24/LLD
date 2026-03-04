package stackOverflow.interfaces;

import stackOverflow.enums.VoteType;
import stackOverflow.model.User;
import stackOverflow.model.Vote;

public interface Votable {
    Vote addOrUpdateVote(User voter, VoteType type);
    VoteType getCurrentVote(User voter);  // null if voter hasn't voted yet
    User getOwner();                      // whose reputation to update
}
