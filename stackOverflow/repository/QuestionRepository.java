package stackOverflow.repository;

import stackOverflow.model.Question;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class QuestionRepository {

    private final List<Question> questions = new CopyOnWriteArrayList<>();

    public void save(Question question) {
        questions.add(question);
    }

    public List<Question> getAll() {
        return Collections.unmodifiableList(questions);
    }
}
