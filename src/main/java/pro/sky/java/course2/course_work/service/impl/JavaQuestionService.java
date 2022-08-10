package pro.sky.java.course2.course_work.service.impl;

import org.springframework.stereotype.Service;
import pro.sky.java.course2.course_work.data.Question;
import pro.sky.java.course2.course_work.exception.QuestionAlreadyExistException;
import pro.sky.java.course2.course_work.exception.QuestionNotFoundException;
import pro.sky.java.course2.course_work.service.QuestionService;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {

    private final Set<Question> questions;
    private final Random random;

    public JavaQuestionService(Random random) {
        this.questions = new HashSet<>();
        this.random = random;
    }

    @Override
    public Question add(String question, String answer) {
        Question q = new Question(question, answer);
        if (questions.contains(q)) {
            throw new QuestionAlreadyExistException();
        }
        questions.add(q);
        return q;
    }

    @Override
    public Question add(Question question) {
        if (questions.contains(question)) {
            throw new QuestionAlreadyExistException();
        }
        questions.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        if (!questions.contains(question)) {
            throw new QuestionNotFoundException();
        }
        questions.remove(question);
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableCollection(questions);
    }

    @Override
    public Question getRandomQuestion() {
        return new ArrayList<>(questions).get(random.nextInt(questions.size()));
    }
}
