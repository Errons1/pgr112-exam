package classQuiz;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MultiChoiceQuizTest {

    @Test
    public void makeMultiChoizeQuize(){
        MultiChoiceQuiz quiz = new MultiChoiceQuiz(
                "question", "1", "somethingA", "somethingB", "somethingC", "somethingD"
        );

        assertEquals("question", quiz.getQuestion());
        assertEquals("1", quiz.getCorrectAnswer());
        assertEquals("somethingA", quiz.getAnswerA());
        assertEquals("somethingB", quiz.getAnswerB());
        assertEquals("somethingC", quiz.getAnswerC());
        assertEquals("somethingD", quiz.getAnswerD());
    }
}
