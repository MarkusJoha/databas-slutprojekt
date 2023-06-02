package DatabaseConnection;

import DatabaseConnection.Database;

import java.sql.*;

public class TableCreator {

    public static void createTables() {
        try {
            Connection conn = Database.getConnection();
            createUser(conn);
            createAccount(conn);
            createTransaction(conn);
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void createUser(Connection conn) {
        try {
            Statement statement = conn.createStatement();

            String query = "CREATE TABLE IF NOT EXISTS users (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "birthdate VARCHAR(20) NOT NULL," +
                    "phone VARCHAR(20) NOT NULL," +
                    "email VARCHAR(50) NOT NULL," +
                    "name VARCHAR(50) NOT NULL," +
                    "address VARCHAR(100)," +
                    "password VARCHAR(100) NOT NULL," +
                    "created DATE DEFAULT (CURRENT_DATE)" +
                    ")";
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("Failed to create user table.");
            e.printStackTrace();
        }
    }

    private static void createAccount(Connection conn) {
        try {
            Statement statement = conn.createStatement();

            String query = "CREATE TABLE IF NOT EXISTS accounts (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "owner_id INT NOT NULL," +
                    "created DATE DEFAULT (CURRENT_DATE)," +
                    "balance INT NOT NULL," +
                    "account_no VARCHAR(20) NOT NULL," +
                    "account_name VARCHAR(100) NOT NULL" +
                    ")";
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("Failed to create user table.");
            e.printStackTrace();
        }
    }

    private static void createTransaction(Connection conn) {
        try {
            Statement statement = conn.createStatement();

            String query = "CREATE TABLE IF NOT EXISTS transactions (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "created DATE DEFAULT (CURRENT_DATE)," +
                    "amount INT NOT NULL," +
                    "sender_acc_id INT NOT NULL," +
                    "receiver_acc_id INT NOT NULL" +
                    ")";
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("Failed to create user table.");
            e.printStackTrace();
        }
    }
}
