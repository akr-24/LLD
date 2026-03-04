package stackOverflow;

import stackOverflow.enums.VoteType;
import stackOverflow.model.Answer;
import stackOverflow.model.Question;
import stackOverflow.model.Tag;
import stackOverflow.model.User;
import stackOverflow.repository.QuestionRepository;
import stackOverflow.service.ReputationScoreService;
import stackOverflow.service.SearchService;
import stackOverflow.service.VotingService;

import java.util.List;

public class StackOverflowMain {

    public static void main(String[] args) {

        // --- Bootstrap services ---
        QuestionRepository questionRepo     = new QuestionRepository();
        ReputationScoreService reputationSvc = new ReputationScoreService();
        VotingService votingService          = new VotingService(reputationSvc);
        SearchService searchService          = new SearchService(questionRepo);

        // --- Create users ---
        User alice = new User("alice");
        User bob   = new User("bob");
        User carol = new User("carol");

        // --- Post questions ---
        Tag javaTag       = new Tag("java");
        Tag concurrencyTag = new Tag("concurrency");

        Question q1 = alice.postQuestion(
                "What is the difference between HashMap and ConcurrentHashMap?",
                List.of(javaTag, concurrencyTag)
        );
        questionRepo.save(q1);

        Question q2 = bob.postQuestion("How does garbage collection work in Java?");
        q2.addTag(javaTag);
        questionRepo.save(q2);

        // --- Post answers ---
        Answer a1 = bob.postAnswer(
                "ConcurrentHashMap uses segment-level locking, HashMap does not.", q1);

        carol.postAnswer("GC identifies unreachable objects and reclaims their memory.", q2);

        // --- Comment on a question and an answer ---
        alice.comment("Great question!", q1);
        bob.comment("Could you add a code example?", a1);

        // --- Vote on questions and answers ---
        // bob and carol upvote alice's question → alice gets +10 each
        votingService.castVote(bob,   q1, VoteType.UPVOTE);
        votingService.castVote(carol, q1, VoteType.UPVOTE);

        // alice upvotes bob's answer → bob gets +10
        votingService.castVote(alice, a1, VoteType.UPVOTE);

        // carol downvotes bob's answer → bob gets -5
        votingService.castVote(carol, a1, VoteType.DOWNVOTE);

        // bob changes his upvote on q1 to downvote
        // → alice loses the +10 from bob and takes a -5 instead
        votingService.castVote(bob, q1, VoteType.DOWNVOTE);

        printReputation(alice, bob, carol);
        printVoteCounts(q1, a1);

        // --- Search ---
        System.out.println("\n=== Search by keyword: 'HashMap' ===");
        printQuestions(searchService.searchByKeyword("HashMap"));

        System.out.println("\n=== Search by tag: 'java' ===");
        printQuestions(searchService.searchByTag("java"));

        System.out.println("\n=== Search by user: bob ===");
        printQuestions(searchService.searchByUser(bob));
    }

    // ---- helpers ----

    private static void printReputation(User... users) {
        System.out.println("\n=== Reputation Scores ===");
        for (User u : users) {
            System.out.printf("  %-8s : %d%n", u.getUserId(), u.getReputationScore());
        }
    }

    private static void printVoteCounts(Question q, Answer a) {
        System.out.println("\n=== Vote Counts ===");
        System.out.printf("  Question  (%s...) : %d%n",
                q.getQuestionContent().substring(0, 20), q.getVoteCount());
        System.out.printf("  Answer    (%s...) : %d%n",
                a.getContent().substring(0, 20), a.getVoteCount());
    }

    private static void printQuestions(List<Question> questions) {
        if (questions.isEmpty()) {
            System.out.println("  (no results)");
        }
        for (Question q : questions) {
            System.out.printf("  [%s] \"%s\"%n", q.getUser().getUserId(), q.getQuestionContent());
        }
    }
}
