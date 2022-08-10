package pro.sky.java.course2.course_work.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.java.course2.course_work.data.Question;
import pro.sky.java.course2.course_work.service.QuestionService;

import java.util.Collection;

@RestController
@RequestMapping("/java")
public class JavaQuestionController {

    private final QuestionService questionService;

    public JavaQuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    public Question addQuestion(String question, String answer);

    public Collection<Question> getQuestions();

    public Question removeQuestion(String question, String answer);

}
