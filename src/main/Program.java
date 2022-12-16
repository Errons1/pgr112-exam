import classUser.User;
import jdbc.ConnectionMySQL;
import menu.AddQuizQuestions;
import menu.ChangeUsername;
import menu.PlayQuiz;
import menu.ViewHighScores;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

public class Program {
    private Connection connection;
    private User user;

    /*
    *   Makes connection to MySQL
    *   Makes User
    *   Print mainMenu
    * */
    public void runProgram(){
        connection = new ConnectionMySQL().connection();
        if (connection == null){
            System.out.println("Could not connect to MySQL, turning off.");
            return;
        }

        user = new ChangeUsername().userName();
        printMainMenu();
        mainMenu();
    }

    public void printMainMenu(){
        ArrayList<String> menu = new ArrayList<>();
        menu.add("1. - Play an Quiz.");
        menu.add("2. - Add a Quiz question.");
        menu.add("3. - View High scores.");
        menu.add("4. - Change username.");
        menu.add("0. - Exit game.");

        for(String s : menu){
            System.out.println(s);
        }
    }

    public void mainMenu(){
        final Scanner scanner = new Scanner(System.in);
        String input;

        menu:
        while (true){
            input = scanner.nextLine();
            switch (input){
                case "1" -> {
                    new PlayQuiz().play(connection, user);
                    connection = new ConnectionMySQL().connection();
                    printMainMenu();
                }
                case "2" -> {
                    new AddQuizQuestions().add(connection);
                    connection = new ConnectionMySQL().connection();
                    printMainMenu();
                }
                case "3" -> {
                    new ViewHighScores().view(connection);
                    connection = new ConnectionMySQL().connection();
                    printMainMenu();
                }
                case "4" -> {
                    user = new ChangeUsername().userName();
                    printMainMenu();
                }
                case "0" -> {
                    System.out.println("Have a nice day!\nShutting off game.");
                    break menu;
                }
                default -> System.out.println("Invalid input.");
            }
        }

    }

}
