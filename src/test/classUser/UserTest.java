package classUser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void makeBasicUser(){
        User user = new User("test");

        assertEquals("test", user.getName());
        assertEquals(0, user.getScore());
        assertEquals("", user.getCurrentQuiz());
    }

    @Test
    public void makeCustomUser(){
        User user = new User("test", 10, "quiz");

        assertEquals("test", user.getName());
        assertEquals(10, user.getScore());
        assertEquals("quiz", user.getCurrentQuiz());
    }

}
