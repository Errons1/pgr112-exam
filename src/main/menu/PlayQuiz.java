package menu;

import classQuiz.BinaryQuiz;
import classQuiz.MultiChoiceQuiz;
import classQuiz.Quiz;
import classUser.User;
import jdbc.ConnectionMySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class PlayQuiz {

    private final ArrayList<Quiz> quizArrayList = new ArrayList<>();
    private Connection connection;
    private final Scanner scanner = new Scanner(System.in);
    private User user;
    private String input;

    public void play(Connection connection, User user) {
        this.connection = connection;
        this.user = user;

        System.out.println("1. - Play multiple choice Gaming quiz.");
        System.out.println("2. - Play binary Programming quiz.");
        System.out.println("0. - Return to menu.");

        menu:
        while (true){
            input = scanner.nextLine();
            switch (input){
                case "1" -> {
                    user.setCurrentQuiz("Multiple choice");
                    makeQuizArrayList(input);
                    break menu;
                }
                case "2" -> {
                    user.setCurrentQuiz("Binary choice");
                    makeQuizArrayList(input);
                    break menu;
                }
                case "0" -> {
                    break menu;
                }
                default -> System.out.println("Invalid input.");
            }
        }

        playQuizGame();
        saveHighScore();
        new ViewHighScores().viewTopFive(this.connection, this.user);
        System.out.println("Your score is:\n" + user + "\n");
        user.setScore(0);
        user.setCurrentQuiz("");
    }

    private void makeQuizArrayList(String input){
        try {
            PreparedStatement statement;

//            Array for multiQuiz
            if (input.equals("1")){
                statement = connection.prepareStatement(
                        "SELECT * FROM quizdb.multichoicequiz"
                );
                ResultSet tmp = statement.executeQuery();

//                Extracts and makes quiz objects from ResultSet
                while (tmp.next()){
                    String[] quizArray = new String[6];
//                    Staring from 2 cause colum 1 is ID
                    for (int i = 2; i <= tmp.getMetaData().getColumnCount(); i++) {
                        quizArray[i-2] = tmp.getString(i);
                    }

                    MultiChoiceQuiz quiz = new MultiChoiceQuiz(quizArray[0], quizArray[1], quizArray[2], quizArray[3], quizArray[4], quizArray[5]);
                    quizArrayList.add(quiz);
                }
            }
//            Array for binaryQuiz
            if (input.equals("2")){
                statement = connection.prepareStatement(
                        "SELECT * FROM quizdb.binaryquiz"
                );
                ResultSet tmp = statement.executeQuery();

//                Extracts and makes quiz objects from ResultSet
                while (tmp.next()){
                    String[] quizArray = new String[2];
//                    Staring from 2 cause colum 1 is ID
                    for (int i = 2; i <= tmp.getMetaData().getColumnCount(); i++) {
                        quizArray[i-2] = tmp.getString(i);
                    }

                    BinaryQuiz quiz = new BinaryQuiz(quizArray[0], quizArray[1]);
                    quizArrayList.add(quiz);
                }
            }

            connection.commit();
            connection.close();
            connection = new ConnectionMySQL().connection();

//            General error handling quiz's from MySQL
        } catch (SQLException e) {
            try {
                System.out.println("Could not get Quiz's from MySQL.");
                connection.rollback();
                connection.close();

//                If rollback did not execute, that is bad...
            } catch (SQLException ex) {
                System.out.println("CRITICAL ERROR: Could not rollback last query.");
            }
        }
    }

    private void playQuizGame(){
//        Makes the arraylist have show different question
        Collections.shuffle(quizArrayList);

//        the quiz will never show more than 5 questions. if the total number is lower that is showed instead
        for (int i = 0; i < quizArrayList.size() && i < 5; i++) {
            if (quizArrayList.size() >= 5) System.out.println("Question "+(i+1)+ " out of 5.");
            else System.out.println("Question "+(i+1)+ " out of " +quizArrayList.size()+ ".");
            System.out.println(quizArrayList.get(i));

//            register user input and make correct response
            while (true){
                input = scanner.nextLine();
                if (input.equals(quizArrayList.get(i).getCorrectAnswer())){
                    user.setScore(user.getScore()+1);
                    System.out.println("Correct answer!\n");
                    break;

                }else if (input.equals("1") || input.equals("2") || input.equals("3") || input.equals("4")){
                    System.out.println("Wrong answer!");
                    System.out.println("Correct answer is " +quizArrayList.get(i).getCorrectAnswer()+ ".\n");
                    break;

                }else{
                    System.out.println("Invalid input.");
                }
            }
        }
    }

    private void saveHighScore(){
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO quizdb.score (username, score, topic) " +
                        "VALUE (?, ?, ?)"
            );
            statement.setString(1, user.getName());
            statement.setInt(2, user.getScore());
            statement.setString(3, user.getCurrentQuiz());

            statement.execute();
            connection.commit();
            connection.close();
            connection = new ConnectionMySQL().connection();

//            When something goes wrong, do a rollback
        } catch (SQLException e) {
            try {
                System.out.println("Could not save result to MySQL.");
                connection.rollback();
                connection.close();

//                If rollback did not execute, that is bad...
            } catch (SQLException ex) {
                System.out.println("CRITICAL ERROR: Could not rollback last query.");
            }
        }
    }



}
