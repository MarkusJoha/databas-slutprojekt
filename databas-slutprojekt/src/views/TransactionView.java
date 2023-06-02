package views;

import controllers.TransactionController;
import models.Transaction;
import models.User;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class TransactionView {
    public static void displaySendMoneyPrompt(User user) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter sender's account name: ");
        String senderAccountName = scanner.nextLine();

        System.out.println("Enter receiver's account name: ");
        String receiverAccountName = scanner.nextLine();

        System.out.println("Enter the amount to transfer: ");
        int amount = scanner.nextInt();
        TransactionController transactionController = new TransactionController();

        int userId = user.getId();
        boolean success = transactionController.transferFunds(senderAccountName, receiverAccountName, amount, userId);

        if (success) {
            System.out.println("Funds transferred successfully.");
        } else {
            System.out.println("Transaction failed.");
        }
    }
    public static void displayTransactionDatePrompt(User user) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the start date (yyyy-MM-dd): ");
        String startDateString = scanner.nextLine();
        Date startDate = parseDate(startDateString);

        System.out.println("Enter the end date (yyyy-MM-dd): ");
        String endDateString = scanner.nextLine();
        Date endDate = parseDate(endDateString);

        TransactionController transactionController = new TransactionController();
        List<Transaction> transactions = transactionController.getTransactionsBetweenDates(user.getId(), startDate, endDate);

        if (!transactions.isEmpty()) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String formattedStartDate = dateFormat.format(startDate);
            String formattedEndDate = dateFormat.format(endDate);

            System.out.println("Transactions between " + formattedStartDate + " and " + formattedEndDate + ":");
            for (Transaction transaction : transactions) {
                String senderAccountName = transaction.getSenderAccountName();
                String receiverAccountName = transaction.getReceiverAccountName();

                if (senderAccountName == null) {
                    senderAccountName = "deleted account";
                }

                if (receiverAccountName == null) {
                    receiverAccountName = "deleted account";
                }

                System.out.println("Sender: " + senderAccountName);
                System.out.println("Receiver: " + receiverAccountName);
                System.out.println("Amount: " + transaction.getAmount());
                System.out.println("Date: " + dateFormat.format(transaction.getCreated()));
                System.out.println();
            }
        } else {
            System.out.println("No transactions found between the specified dates.");
        }
    }
    private static Date parseDate(String dateString) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter the date in the format yyyy-MM-dd.");
            return new Date();
        }
    }
}