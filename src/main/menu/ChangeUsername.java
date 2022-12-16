package menu;

import classUser.User;

import java.util.Scanner;

public class ChangeUsername {

    public User userName(){
        Scanner scanner = new Scanner(System.in);
        String input;

        System.out.print("Enter your username: ");
        User user = new User(input = scanner.nextLine());

        System.out.println("\nWelcome " + input + "! To QuizGame!");
        return user;
    }

}
