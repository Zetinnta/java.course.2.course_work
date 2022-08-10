package pro.sky.java.course2.course_work.service;

import pro.sky.java.course2.course_work.data.Question;

import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getQuestions(int amount);
}
