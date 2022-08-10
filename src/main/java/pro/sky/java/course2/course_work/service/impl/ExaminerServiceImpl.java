package pro.sky.java.course2.course_work.service.impl;

import org.springframework.stereotype.Service;
import pro.sky.java.course2.course_work.data.Question;
import pro.sky.java.course2.course_work.exception.NotEnoughQuestionsException;
import pro.sky.java.course2.course_work.service.ExaminerService;
import pro.sky.java.course2.course_work.service.QuestionService;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        if (amount <= 0 || amount > questionService.getAll().size()) {
            throw new NotEnoughQuestionsException();
        }

        Set<Question> result = new HashSet<>(amount);

        while (result.size() < amount) {
            result.add(questionService.getRandomQuestion());
        }

        return result;
    }
}
