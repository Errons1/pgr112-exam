package menu;

import classUser.User;
import jdbc.ConnectionMySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class
ViewHighScores {

    private Connection connection;
    private final Scanner scanner = new Scanner(System.in);
    private String input;

    public void view(Connection connection){
        this.connection = connection;
        printMenu();

        menu:
        while (true){
            input = scanner.nextLine();
            switch (input){
                case "1" -> {
                    viewAll();
                    printMenu();
                }
                case "2" -> {
                    viewTopFive(this.connection, null);
                    printMenu();
                }
                case "3" -> {
                    viewByUser();
                    printMenu();
                }
                case "4" -> {
                    viewAboveMinValue();
                    printMenu();
                }
                case "0" -> {
                    break menu;
                }
                default -> System.out.println("Invalid input.");
            }
        }

    }

    private void printMenu(){
        ArrayList<String> menu = new ArrayList<>();
        menu.add("1. - View full High score list.");
        menu.add("2. - View top five High score list.");
        menu.add("3. - View sorted High score list by user");
        menu.add("4. - View sorted High score list by min. score.");
        menu.add("0. - Return to main menu.");

        for (String s : menu){
            System.out.println(s);
        }
    }

    protected void viewTopFive(Connection connection, User user){
        try {
            this.connection = connection;
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM quizdb.score " +
                        "WHERE topic LIKE ? " +
                        "ORDER BY score " +
                        "DESC LIMIT 5;"
            );
//            Get general top 5 if it's not called after a game
            if (user == null) statement.setString(1, "%");
            else statement.setString(1, user.getCurrentQuiz());

//            Extracts and makes User objects from ResultSet
            ResultSet tmp = statement.executeQuery();
            ArrayList<User> userArrayList = parseResultSet(tmp);
            printResult(userArrayList);
            this.connection.commit();
            this.connection.close();
            this.connection = new ConnectionMySQL().connection();

//            When something goes wrong, do a rollback
        } catch (SQLException e) {
            try {
                System.out.println("Could not get top five list.");
                this.connection.rollback();
                this.connection.close();

//                If rollback did not execute, that is bad...
            } catch (SQLException ex) {
                System.out.println("CRITICAL ERROR: Could not rollback last query.");
            }
        }

    }

    private void viewAll(){
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM quizdb.score "
            );

//            Extracts and makes User objects from ResultSet
            ResultSet tmp = statement.executeQuery();
            ArrayList<User> userArrayList = parseResultSet(tmp);
            printResult(userArrayList);
            connection.commit();
            connection.close();
            connection = new ConnectionMySQL().connection();

//            When something goes wrong, do a rollback
        } catch (SQLException e) {
            try {
                System.out.println("Could not get full list.");
                connection.rollback();
                connection.close();

//                If rollback did not execute, that is bad...
            } catch (SQLException ex) {
                System.out.println("CRITICAL ERROR: Could not rollback last query.");
            }
        }
    }

    private void viewByUser(){
        System.out.println("Enter username you want to filter with: ");
        String user = scanner.nextLine();

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM quizdb.score " +
                        "WHERE score.username LIKE ? "
            );
            statement.setString(1, user);

//            Extracts and makes User objects from ResultSet
            ResultSet tmp = statement.executeQuery();
            ArrayList<User> userArrayList = parseResultSet(tmp);
            printResult(userArrayList);
            connection.commit();
            connection.close();
            this.connection = new ConnectionMySQL().connection();

//            When something goes wrong, do a rollback
        } catch (SQLException e) {
            try {
                System.out.println("Could not get sorted list by username");
                connection.rollback();
                connection.close();

//                If rollback did not execute, that is bad...
            } catch (SQLException ex) {
                System.out.println("CRITICAL ERROR: Could not rollback last query.");
            }
        }
    }

    private void viewAboveMinValue(){
        System.out.println("Note: search can be done with 0 - 5.");
        System.out.println("Enter score you want to filter with: ");
        String scoreString;
        int score;
        while (true){
            scoreString = scanner.nextLine();
            try {
                score = Integer.parseInt(scoreString);
                if (score < 0 || score > 5){
                    System.out.println("Invalid range.");
                    continue;
                }
                break;
            }catch (NumberFormatException e){
                System.out.println("Invalid input.");
            }
        }

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM quizdb.score " +
                            "WHERE score.score >= ? " +
                            "ORDER BY score DESC "
            );
            statement.setInt(1, score);

//            Extracts and makes User objects from ResultSet
            ResultSet tmp = statement.executeQuery();
            ArrayList<User> userArrayList = parseResultSet(tmp);
            printResult(userArrayList);
            connection.commit();
            connection.close();
            this.connection = new ConnectionMySQL().connection();

//            When something goes wrong, do a rollback
        } catch (SQLException e) {
            try {
                System.out.println("Could not get list sorted by min. value.");
                connection.rollback();
                connection.close();

//                If rollback did not execute, that is bad...
            } catch (SQLException ex) {
                System.out.println("CRITICAL ERROR: Could not rollback last query.");
            }
        }
    }

    private ArrayList<User> parseResultSet(ResultSet tmp) throws SQLException{
        ArrayList<User> userArrayList = new ArrayList<>();

        while (tmp.next()){
            String[] userArray = new String[3];
//                    Staring from 2 cause colum 1 is ID
            for (int i = 2; i <= tmp.getMetaData().getColumnCount(); i++) {
                userArray[i-2] = tmp.getString(i);
            }

            User quiz = new User(userArray[0], Integer.parseInt(userArray[1]), userArray[2]);
            userArrayList.add(quiz);
        }

        return userArrayList;
    }

    private void printResult(ArrayList<User> arrayList){
        System.out.println("Username    Score    Topic");
        for (User u : arrayList){
            System.out.println(u);
        }
        System.out.println();
    }

}
