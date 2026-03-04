package stackOverflow.service;

import stackOverflow.enums.VoteType;
import stackOverflow.interfaces.Votable;
import stackOverflow.model.User;
import stackOverflow.model.Vote;

public class VotingService {

    private final ReputationScoreService reputationService;

    public VotingService(ReputationScoreService reputationService) {
        this.reputationService = reputationService;
    }

    public Vote castVote(User voter, Votable target, VoteType type) {
        VoteType previousVote = target.getCurrentVote(voter);
        Vote vote = target.addOrUpdateVote(voter, type);
        reputationService.onVote(target.getOwner(), previousVote, type);
        return vote;
    }
}
