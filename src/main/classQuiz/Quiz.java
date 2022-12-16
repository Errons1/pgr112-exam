package classQuiz;

public abstract class Quiz {

    private String question;
    private String correctAnswer;

    public Quiz(String question, String correctAnswer){
        this.question = question;
        this.correctAnswer = correctAnswer;
    }

    @Override
    public abstract String toString();

//    Getters & setters
    public String getQuestion() {
        return question;
    }
    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
