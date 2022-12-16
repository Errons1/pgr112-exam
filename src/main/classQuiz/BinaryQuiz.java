package classQuiz;

public class BinaryQuiz extends Quiz{

    public BinaryQuiz(String question, String correctAnswer){
        super(question, correctAnswer);
    }

    @Override
    public String toString() {
        return (
                super.getQuestion()+"?\n" +
                "1. - True.\n" +
                "2. - False.\n");
    }

//    Getters & setters

}
