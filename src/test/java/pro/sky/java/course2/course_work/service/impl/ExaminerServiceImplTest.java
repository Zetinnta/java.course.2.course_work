package pro.sky.java.course2.course_work.service.impl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.java.course2.course_work.data.Question;
import pro.sky.java.course2.course_work.exception.NotEnoughQuestionsException;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class ExaminerServiceImplTest {

    @Mock
    private JavaQuestionService javaQuestionService;

    @InjectMocks
    private ExaminerServiceImpl examinerService;

    @Test
    public void getQuestionsNegativeTest() {
        assertThatExceptionOfType(NotEnoughQuestionsException.class)
                .isThrownBy(() -> examinerService.getQuestions(-1));

        assertThatExceptionOfType(NotEnoughQuestionsException.class)
                .isThrownBy(() -> examinerService.getQuestions(1));
    }

    @Test
    public void getQuestionsPositiveTest() {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("Question1", "Answer1"));
        questions.add(new Question("Question2", "Answer2"));
        questions.add(new Question("Question3", "Answer3"));
        questions.add(new Question("Question4", "Answer4"));
        questions.add(new Question("Question5", "Answer5"));

        when(javaQuestionService.getAll()).thenReturn(questions);

        when(javaQuestionService.getRandomQuestion())
                .thenReturn(questions.get(0), questions.get(1), questions.get(0), questions.get(2));
        assertThat(examinerService.getQuestions(3)).containsExactlyInAnyOrder(questions.get(0), questions.get(1), questions.get(2));

        when(javaQuestionService.getRandomQuestion())
                .thenReturn(questions.get(0), questions.get(1), questions.get(0), questions.get(2), questions.get(3), questions.get(1));
        assertThat(examinerService.getQuestions(4)).containsExactlyInAnyOrder(questions.get(0), questions.get(1), questions.get(2), questions.get(3));
    }
}
