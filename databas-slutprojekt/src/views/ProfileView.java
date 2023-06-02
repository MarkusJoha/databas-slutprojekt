package views;

import controllers.UserController;
import models.User;
import java.util.Scanner;

public class ProfileView {
    public static void profileMenu(User user) {

        while (true) {
            showUserInformation(user);
            System.out.println("1. Update information.");
            System.out.println("2. Go back.");
            switch (new Scanner(System.in).nextLine()) {
                case "1" -> updateUserInfo(user);
                case "2" -> {
                    return;
                }

                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    public static void showUserInformation(User user) {
        System.out.println("User Information:");
        System.out.println("Name: " + user.getName());
        System.out.println("Address: " + user.getAddress());
        System.out.println("Phone: " + user.getPhone());
        System.out.println("Password: " + user.getPassword());
        System.out.println("Email: " + user.getEmail());
    }
        private static void updateUserInfo(User user) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter the number of the information you want to update:");
            System.out.println("1. Name");
            System.out.println("2. Address");
            System.out.println("3. Phone");
            System.out.println("4. Password");
            System.out.println("5. Email");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> {
                    System.out.println("Enter the new name:");
                    String name = scanner.nextLine();
                    user.setName(name);
                }
                case "2" -> {
                    System.out.println("Enter the new address:");
                    String address = scanner.nextLine();
                    user.setAddress(address);
                }
                case "3" -> {
                    System.out.println("Enter the new phone number:");
                    String phone = scanner.nextLine();
                    user.setPhone(phone);
                }
                case "4" -> {
                    System.out.println("Enter the new password:");
                    String password = scanner.nextLine();
                    user.setPassword(password);
                }
                case "5" -> {
                    System.out.println("Enter the new email:");
                    String email = scanner.nextLine();
                    user.setEmail(email);
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
            UserController userController = new UserController();
            userController.update(user);
    }

    public static boolean deleteUser(User user) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Are you sure you want to delete your account? (yes/no)");

        String confirmation = scanner.nextLine().toLowerCase();

        if (confirmation.equals("yes") || confirmation.equals("y")) {
            System.out.println("Please enter your birthdate: ");
            String birthdate = scanner.nextLine();
            System.out.println("Please enter your password: ");
            String password = scanner.nextLine();

            if (birthdate.equals(user.getBirthdate()) && password.equals(user.getPassword())) {
                UserController userController = new UserController();
                userController.delete(user);
                System.out.println("User successfully deleted.");
                return true;
            } else {
                System.out.println("Invalid birthdate or password. Deletion canceled.");
            }
        } else {
            System.out.println("Cancelling delete process.");
        }
       return false;
    }
}

