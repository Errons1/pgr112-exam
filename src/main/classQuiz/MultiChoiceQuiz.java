package classQuiz;

public class MultiChoiceQuiz extends Quiz{

    private String answerA;
    private String answerB;
    private String answerC;
    private String answerD;

    public MultiChoiceQuiz(String question, String correctAnswer, String answerA, String answerB, String answerC, String answerD){
        super(question, correctAnswer);
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.answerD = answerD;
    }

    @Override
    public String toString() {
        return (
                super.getQuestion()+"?\n" +
                "1. - "+getAnswerA()+".\n" +
                "2. - "+getAnswerB()+".\n" +
                "3. - "+getAnswerC()+".\n" +
                "4. - "+getAnswerD()+".\n");
    }

//    Getters & setters
    public String getAnswerA() {
        return answerA;
    }
    public String getAnswerB() {
        return answerB;
    }
    public String getAnswerC() {
        return answerC;
    }
    public String getAnswerD() {
        return answerD;
    }

    public void setAnswerA(String answerA) {
        this.answerA = answerA;
    }
    public void setAnswerB(String answerB) {
        this.answerB = answerB;
    }
    public void setAnswerC(String answerC) {
        this.answerC = answerC;
    }
    public void setAnswerD(String answerD) {
        this.answerD = answerD;
    }

}
