package controllers;

import DatabaseConnection.Database;
import models.Account;
import models.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AccountController extends Database{

    public AccountController() {

    }

    public List<Account> getAccounts(User user) {
        List<Account> accounts = new ArrayList<>();

        try {
            Connection conn = Database.getConnection();

            PreparedStatement selectStmt = conn.prepareStatement("SELECT * FROM accounts WHERE owner_id = ?");
            selectStmt.setInt(1, user.getId());
            ResultSet resultSet = selectStmt.executeQuery();

            while (resultSet.next()) {
                int accountId = resultSet.getInt("id");
                int ownerId = resultSet.getInt("owner_id");
                String accountName = resultSet.getString("account_name");
                String accountNo = resultSet.getString("account_no");
                int balance = resultSet.getInt("balance");
                Date created = resultSet.getDate("created");



                Account account = new Account(accountId, ownerId, balance, accountNo, accountName, created);
                accounts.add(account);
            }

            conn.close();
        } catch (SQLException e) {
            System.out.println("Error occurred while fetching bank accounts.");
            e.printStackTrace();
        }

        return accounts;
    }

    public void createAccount(User user, Account account) {
        try {
            Connection conn = Database.getConnection();

            PreparedStatement checkStmt = conn.prepareStatement("SELECT COUNT(*) FROM accounts WHERE account_name = ?");
            checkStmt.setString(1, account.getAccountName());
            ResultSet checkResult = checkStmt.executeQuery();
            checkResult.next();
            int count = checkResult.getInt(1);

            if (count > 0) {
                System.out.println("An account with that name already exists.");
                conn.close();
                return;
            }

            PreparedStatement insertStmt = conn.prepareStatement("INSERT INTO accounts (account_name, account_no, balance, owner_id) VALUES (?, ?, ?, ?)");
            insertStmt.setString(1, account.getAccountName());
            insertStmt.setString(2, account.getAccountNo());
            insertStmt.setInt(3, account.getBalance());
            insertStmt.setInt(4, user.getId()); // Set the owner ID to the ID of the user

            if (insertStmt.executeUpdate() == 1) {
                System.out.println("Bank account created.");
            } else {
                System.out.println("Failed to create bank account. Please try again.");
            }

            conn.close();
        } catch (SQLException e) {
            System.out.println("Error occurred while creating bank account.");
            e.printStackTrace();
        }
    }
    public void updateAccount(Account account) {
        try {
            Connection conn = Database.getConnection();

            PreparedStatement updateStmt = conn.prepareStatement("UPDATE accounts SET balance = ? WHERE id = ?");
            updateStmt.setInt(1, account.getBalance());
            updateStmt.setInt(2, account.getId());

            if (updateStmt.executeUpdate() == 1) {
                System.out.println("Bank account updated.");
            } else {
                System.out.println("Failed to update bank account. Please try again.");
            }

            conn.close();
        } catch (SQLException e) {
            System.out.println("Error occurred while updating bank account.");
            e.printStackTrace();
        }
    }
    public void deleteAccount(Account account) {
        try {
            Connection conn = Database.getConnection();

            PreparedStatement deleteStmt = conn.prepareStatement("DELETE FROM accounts WHERE id = ?");
            deleteStmt.setInt(1, account.getId());

            if (deleteStmt.executeUpdate() == 1) {
                System.out.println("Bank account deleted.");
            } else {
                System.out.println("Failed to delete bank account. Please try again.");
            }

            conn.close();
        } catch (SQLException e) {
            System.out.println("Error occurred while deleting bank account.");
            e.printStackTrace();
        }
    }
}