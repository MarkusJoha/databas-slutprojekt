package controllers;

import DatabaseConnection.Database;
import Hashing.Password;
import models.User;
import views.MenuView;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

public class MenuController extends Database{
    public static void createUser() {
        MenuView.registerUser();
    }

    public static User logIn() {
        Scanner input = new Scanner(System.in);
        try {
            String checkPassword = "SELECT * FROM users WHERE birthdate = ?;";

            System.out.println("Log In");
            System.out.println("Birthdate:");
            String username = input.nextLine();

            System.out.println("Password:");
            String password = input.nextLine();

            Connection connection = Database.getConnection();
            PreparedStatement checkPw = connection.prepareStatement(checkPassword);
            checkPw.setString(1, username);
            ResultSet pw = checkPw.executeQuery();
            if (pw.next()) {
                String hashPassword = pw.getString("password");

                if (Password.Verify(password, hashPassword)) {
                    System.out.println("Login successful!");
                    int userId = pw.getInt("id");
                    String name = pw.getString("name");
                    String address = pw.getString("address");
                    String phone = pw.getString("phone");
                    String birthdate = pw.getString("birthdate");
                    String email = pw.getString("email");
                    Date created = pw.getDate("created");

                    System.out.println("Welcome " + name + "!");
                    connection.close();

                    return new User(userId, birthdate, phone, email, name, address, password, created);
                } else {
                    System.out.println("Wrong password!");
                }
            } else {
                System.out.println("Birthdate doesn't exist!");
            }
            connection.close();
            return null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
