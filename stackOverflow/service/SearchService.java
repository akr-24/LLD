package stackOverflow.service;

import stackOverflow.model.Question;
import stackOverflow.model.Tag;
import stackOverflow.model.User;
import stackOverflow.repository.QuestionRepository;

import java.util.List;
import java.util.stream.Collectors;

public class SearchService {

    private final QuestionRepository questionRepository;

    public SearchService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public List<Question> searchByKeyword(String keyword) {
        String lower = keyword.toLowerCase();
        return questionRepository.getAll().stream()
                .filter(q -> q.getQuestionContent().toLowerCase().contains(lower))
                .collect(Collectors.toList());
    }

    public List<Question> searchByTag(String tagName) {
        return questionRepository.getAll().stream()
                .filter(q -> q.getTags().stream()
                        .map(Tag::getName)
                        .anyMatch(name -> name.equalsIgnoreCase(tagName)))
                .collect(Collectors.toList());
    }

    public List<Question> searchByUser(User user) {
        return questionRepository.getAll().stream()
                .filter(q -> q.getUser().getUserId().equals(user.getUserId()))
                .collect(Collectors.toList());
    }
}
