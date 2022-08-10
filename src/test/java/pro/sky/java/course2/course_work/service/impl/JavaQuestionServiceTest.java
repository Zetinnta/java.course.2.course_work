package pro.sky.java.course2.course_work.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pro.sky.java.course2.course_work.data.Question;
import pro.sky.java.course2.course_work.exception.QuestionAlreadyExistException;
import pro.sky.java.course2.course_work.exception.QuestionNotFoundException;
import pro.sky.java.course2.course_work.service.QuestionService;

import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class JavaQuestionServiceTest {

    private final QuestionService questionService = new JavaQuestionService();

    @ParameterizedTest
    @MethodSource("questionOne")
    public void addOneTest(Question question) {
        questionService.add(question);
        assertThatExceptionOfType(QuestionAlreadyExistException.class)
                .isThrownBy(() -> questionService.add(question));
        assertThat(questionService.getAll()).containsExactlyInAnyOrder(question);
    }

    @ParameterizedTest
    @MethodSource("questionTwo")
    public void addTwoTest(String question, String answer) {
        questionService.add(question, answer);
        assertThatExceptionOfType(QuestionAlreadyExistException.class)
                .isThrownBy(() -> questionService.add(question, answer));
        assertThat(questionService.getAll()).containsExactlyInAnyOrder(new Question(question, answer));
    }

    @ParameterizedTest
    @MethodSource("questionOne")
    public void removeTest(Question question) {
        questionService.add(question);
        questionService.remove(question);
        assertThat(questionService.getAll()).isEmpty();
        assertThatExceptionOfType(QuestionNotFoundException.class)
                .isThrownBy(() -> questionService.remove(question));
    }

    @ParameterizedTest
    @MethodSource("questions")
    public void getRandomQuestionTest(Set<Question> questions) {
        questions.forEach(questionService::add);
        assertThat(questionService.getAll()).hasSize(questions.size());
        assertThat(questionService.getRandomQuestion()).isIn(questionService.getAll());
    }

    public static Stream<Arguments> questionOne() {
        return Stream.of(
                Arguments.of(new Question("Question", "Answer"))
        );
    }

    public static Stream<Arguments> questionTwo() {
        return Stream.of(
                Arguments.of("Question", "Answer")
        );
    }

    public static Stream<Arguments> questions() {
        return Stream.of(
                Arguments.of(
                        Set.of(
                                new Question("Question1", "Answer1)"),
                                new Question("Question2", "Answer2"),
                                new Question("Question3", "Answer3")))
        );
    }
}
