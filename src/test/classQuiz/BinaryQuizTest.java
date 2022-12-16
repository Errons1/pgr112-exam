package classQuiz;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BinaryQuizTest {

    @Test
    public void makeBinaryQuiz(){
        BinaryQuiz quiz = new BinaryQuiz("question", "1");

        assertEquals("question", quiz.getQuestion());
        assertEquals("1", quiz.getCorrectAnswer());
    }

}
