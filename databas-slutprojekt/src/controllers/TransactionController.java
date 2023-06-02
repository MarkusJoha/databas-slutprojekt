package controllers;

import DatabaseConnection.Database;
import models.Transaction;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class TransactionController extends Database{

    public boolean transferFunds(String senderAccountName, String receiverAccountName, int amount, int owner_id) {
        try (Connection conn = Database.getConnection();
             PreparedStatement senderStmt = conn.prepareStatement("SELECT * FROM accounts WHERE account_name = ? AND owner_id = ?");
             PreparedStatement receiverStmt = conn.prepareStatement("SELECT * FROM accounts WHERE account_name = ?");
             PreparedStatement updateSenderStmt = conn.prepareStatement("UPDATE accounts SET balance = balance - ? WHERE account_name = ?");
             PreparedStatement updateReceiverStmt = conn.prepareStatement("UPDATE accounts SET balance = balance + ? WHERE account_name = ?");
             PreparedStatement transactionStmt = conn.prepareStatement("INSERT INTO transactions (created, amount, sender_acc_id, receiver_acc_id) VALUES (?, ?, ?, ?)")) {

            senderStmt.setString(1, senderAccountName);
            senderStmt.setInt(2, owner_id);
            ResultSet senderResult = senderStmt.executeQuery();

            receiverStmt.setString(1, receiverAccountName);
            ResultSet receiverResult = receiverStmt.executeQuery();

            if (!senderResult.next() || !receiverResult.next()) {
                System.out.println("Invalid sender or receiver account.");
                return false;
            }

            int senderAccountId = senderResult.getInt("id");
            int receiverAccountId = receiverResult.getInt("id");
            int senderBalance = senderResult.getInt("balance");

            if (senderBalance < amount) {
                System.out.println("Insufficient balance in the sender's account.");
                return false;
            }

            updateSenderStmt.setInt(1, amount);
            updateSenderStmt.setString(2, senderAccountName);
            updateSenderStmt.executeUpdate();

            updateReceiverStmt.setInt(1, amount);
            updateReceiverStmt.setString(2, receiverAccountName);
            updateReceiverStmt.executeUpdate();

            LocalDate currentDate = LocalDate.now();
            java.sql.Date sqlDate = java.sql.Date.valueOf(currentDate);

            transactionStmt.setDate(1, sqlDate);
            transactionStmt.setInt(2, amount);
            transactionStmt.setInt(3, senderAccountId);
            transactionStmt.setInt(4, receiverAccountId);
            transactionStmt.executeUpdate();

            System.out.println("Transaction completed successfully.");
            return true;
        } catch (SQLException e) {
            System.out.println("Error occurred while transferring funds.");
            e.printStackTrace();
            return false;
        }
    }

    public List<Transaction> getTransactionsBetweenDates(int userId, Date startDate, Date endDate) {
        List<Transaction> transactions = new ArrayList<>();
        try {
            Connection conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM transactions WHERE (sender_acc_id IN (SELECT id FROM accounts WHERE owner_id = ?) OR receiver_acc_id IN (SELECT id FROM accounts WHERE owner_id = ?)) AND created BETWEEN ? AND ? ORDER BY created DESC");
            stmt.setInt(1, userId);
            stmt.setInt(2, userId);
            stmt.setDate(3, new java.sql.Date(startDate.getTime()));
            stmt.setDate(4, new java.sql.Date(endDate.getTime()));
            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                int id = result.getInt("id");
                int amount = result.getInt("amount");
                int senderAccountId = result.getInt("sender_acc_id");
                int receiverAccountId = result.getInt("receiver_acc_id");
                java.sql.Date created = result.getDate("created");

                String senderAccountName;
                if (senderAccountId == 0) {
                    senderAccountName = "deleted account";
                } else {
                    senderAccountName = getAccountNameById(senderAccountId);
                }

                String receiverAccountName;
                if (receiverAccountId == 0) {
                    receiverAccountName = "deleted account";
                } else {
                    receiverAccountName = getAccountNameById(receiverAccountId);
                }

                Transaction transaction = new Transaction(id, amount, senderAccountId, receiverAccountId, created);
                transaction.setSenderAccountName(senderAccountName);
                transaction.setReceiverAccountName(receiverAccountName);
                transactions.add(transaction);
            }

            conn.close();
        } catch (SQLException e) {
            System.out.println("Error occurred while retrieving transactions.");
            e.printStackTrace();
        }
        return transactions;
    }

    private String getAccountNameById(int accountId) {
        try {
            Connection conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT account_name FROM accounts WHERE id = ?");
            stmt.setInt(1, accountId);
            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                return result.getString("account_name");
            }

            conn.close();
        } catch (SQLException e) {
            System.out.println("Error occurred while retrieving account name.");
            e.printStackTrace();
        }
        return null;
    }
}

