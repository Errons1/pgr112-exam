package menu;

import classQuiz.BinaryQuiz;
import classQuiz.MultiChoiceQuiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class AddQuizQuestions {

    private Connection connection;
    private final Scanner scanner = new Scanner(System.in);
    private String input;

//    Let user pick what quiz to add
    public void add(Connection connection){
        this.connection = connection;

        System.out.println("1. - Add multiple choice question.");
        System.out.println("2. - Add binary question.");
        System.out.println("0. - Return to menu.");
        menu:
        while (true){
            input = scanner.nextLine();
            switch (input){
                case "1" -> {
                    makeMultiChoice();
                    break menu;
                }
                case "2" -> {
                    makeBinary();
                    break menu;
                }
                case "0" -> {
                    break menu;
                }
                default -> System.out.println("Invalid input.");
            }
        }
    }

//    Builds up na object of type Quiz to be sent
    private void makeMultiChoice(){
//        Series of input for data
        System.out.println("Add a question:");
        String question = scanner.nextLine();
        System.out.println("Add answer 1:");
        String answerA = scanner.nextLine();
        System.out.println("Add answer 2:");
        String answerB = scanner.nextLine();
        System.out.println("Add answer 3:");
        String answerC = scanner.nextLine();
        System.out.println("Add answer 4:");
        String answerD = scanner.nextLine();
        System.out.println("Note: enter a number between 1 - 4");
        System.out.println("Add correct answer:");
        String correctAnswer;
        while (true){
            correctAnswer = scanner.nextLine();
            if (!(correctAnswer.equals("1") || correctAnswer.equals("2") || correctAnswer.equals("3") || correctAnswer.equals("4"))){
                System.out.println("Invalid input.");
                continue;
            }
            break;
        }

//        Confirms if this Quiz is ready for saving
        MultiChoiceQuiz quiz = new MultiChoiceQuiz(question, correctAnswer,answerA, answerB, answerC, answerD);
        System.out.println(quiz);
        System.out.println("Save this question?");
        System.out.println("1. - Save");
        System.out.println("2. - Dont save");
        save:
        while (true){
            input = scanner.nextLine();
            switch (input){
                case "1" -> {
                    saveMultiChoice(quiz);
                    System.out.println("Question saved!");
                    break save;
                }
                case "2" -> {
                    System.out.println("Question discarded.");
                    break save;
                }
                default -> System.out.println("Invalid input.");
            }
        }

    }

//    Saves the new multiQuiz to MySQL
    private void saveMultiChoice(MultiChoiceQuiz quiz){
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO quizdb.multichoicequiz (question, correctAnswer, answerA, answerB, answerC, answerD)" +
                        "VALUE (?, ?, ?, ?, ?, ?)");
            statement.setString(1, quiz.getQuestion());
            statement.setString(2, quiz.getCorrectAnswer());
            statement.setString(3, quiz.getAnswerA());
            statement.setString(4, quiz.getAnswerB());
            statement.setString(5, quiz.getAnswerC());
            statement.setString(6, quiz.getAnswerD());

            statement.execute();
            connection.commit();
            connection.close();

//            When something goes wrong, do a rollback
        } catch (SQLException e) {
            try {
                System.out.println("Could not save Quiz to MySQL.");
                connection.rollback();
                connection.close();

//                If rollback did not execute, that is bad...
            } catch (SQLException ex) {
                System.out.println("CRITICAL ERROR: Could not rollback last query.");
            }
        }

    }

//    Builds up an object of type Quiz to be sent
    private void makeBinary(){
//        Series of input for data
        System.out.println("Add a question:");
        String question = scanner.nextLine();
        System.out.println("Note: 1 = True & 2 = False");
        System.out.println("Add correct answer:");
        String correctAnswer;
        while (true){
            correctAnswer = scanner.nextLine();
            if (!(correctAnswer.equals("1") || correctAnswer.equals("2"))){
                System.out.println("Invalid input.");
                continue;
            }
            break;
        }

//        Confirms if this Quiz is ready for saving
        BinaryQuiz quiz = new BinaryQuiz(question, correctAnswer);
        System.out.println(quiz);
        System.out.println("Save this question?");
        System.out.println("1. - Save");
        System.out.println("2. - Dont save");
        save:
        while (true){
            input = scanner.nextLine();
            switch (input){
                case "1" -> {
                    saveBinary(quiz);
                    System.out.println("Question saved!");
                    break save;
                }
                case "2" -> {
                    System.out.println("Question discarded.");
                    break save;
                }
                default -> System.out.println("Invalid input.");
            }
        }
    }

//    Saves the new binaryQuiz to MySQL
    private void saveBinary(BinaryQuiz quiz){
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO quizdb.binaryquiz (question, correctAnswer)" +
                        "VALUE (?, ?)");
            statement.setString(1, quiz.getQuestion());
            statement.setString(2, quiz.getCorrectAnswer());

            statement.execute();
            connection.commit();
            connection.close();

//            When something goes wrong, do a rollback
        } catch (SQLException e) {
            try {
                System.out.println("Could not save Quiz to MySQL.");
                connection.rollback();
                connection.close();

//                If rollback did not execute, that is bad...
            } catch (SQLException ex) {
                System.out.println("CRITICAL ERROR: Could not rollback last query.");
            }
        }
    }

}