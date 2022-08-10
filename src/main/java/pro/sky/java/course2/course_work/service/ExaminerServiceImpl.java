package pro.sky.java.course2.course_work.service;

import pro.sky.java.course2.course_work.data.Question;

import java.util.Collection;
import java.util.Random;

public class ExaminerServiceImpl implements ExaminerService{

    Random random;
    QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    Collection<Question> getQuestions(int amount);
}
