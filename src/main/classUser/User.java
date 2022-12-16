package classUser;

public class User {

    private String username;
    private int score;
    private String currentQuiz;


    public User(String username){
        this.username = username;
        score = 0;
        currentQuiz = "";
    }

    public User(String username, int score, String currentQuiz){
        this.username = username;
        this.score = score;
        this.currentQuiz = currentQuiz;
    }

    @Override
    public String toString(){
        return (getName() + "    " + getScore() + "    " + getCurrentQuiz());
    }

//    Getters & setters
    public String getName() {
        return username;
    }
    public String getCurrentQuiz() {
        return currentQuiz;
    }
    public int getScore() {
        return score;
    }

    public void setName(String name) {
        this.username = name;
    }
    public void setCurrentQuiz(String currentQuiz) {
        this.currentQuiz = currentQuiz;
    }
    public void setScore(int score) {
        this.score = score;
    }
}
