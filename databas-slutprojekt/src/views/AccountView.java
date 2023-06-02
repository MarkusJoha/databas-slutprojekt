package views;

import controllers.AccountController;
import models.Account;
import models.User;
import java.util.List;
import java.util.Scanner;

public class AccountView {

    public static void accountMenu(User user) {
        while (true) {
            System.out.println("1. Show Bank Accounts");
            System.out.println("2. Create New Bank Account");
            System.out.println("3. Update Bank Account");
            System.out.println("4. Delete Bank Account");
            System.out.println("5. Go Back");

            System.out.print("Choice: ");
            String choice = new Scanner(System.in).nextLine();

            switch (choice) {
                case "1" -> showBankAccounts(user);
                case "2" -> createBankAccount(user);
                case "3" -> updateBankAccount(user);
                case "4" -> deleteBankAccount(user);
                case "5" -> {
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void showBankAccounts(User user) {
        AccountController accountController = new AccountController();
        List<Account> accounts = accountController.getAccounts(user);

        if (accounts.isEmpty()) {
            System.out.println("No bank accounts found.");
        } else {
            System.out.println("Bank Accounts:");
            for (Account account : accounts) {
                System.out.println("Account Name: " + account.getAccountName());
                System.out.println("Account Number: " + account.getAccountNo());
                System.out.println("Balance: " + account.getBalance());
                System.out.println("-----------------------------");
            }
        }
    }

    private static void createBankAccount(User user) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter account name: ");
        String accountName = scanner.nextLine();

        System.out.println("Enter account number: ");
        String accountNo = scanner.nextLine();

        System.out.println("Enter balance: ");
        int balance = Integer.parseInt(scanner.nextLine());

        Account account = new Account();
        account.setAccountName(accountName);
        account.setAccountNo(accountNo);
        account.setBalance(balance);

        AccountController accountController = new AccountController();
        accountController.createAccount(user, account);

        System.out.println("Bank account created successfully!");
    }

    private static void updateBankAccount(User user) {
        AccountController accountController = new AccountController();
        List<Account> accounts = accountController.getAccounts(user);
        if (accounts.isEmpty()) {
            System.out.println("No bank accounts found.");
            return;
        }

        System.out.print("Enter the account name to update: ");
        String accountName = new Scanner(System.in).nextLine();

        Account accountToUpdate = null;
        for (Account account : accounts) {
            if (account.getAccountName().equals(accountName)) {
                accountToUpdate = account;
                break;
            }
        }
        if (accountToUpdate == null) {
            System.out.println("Account not found.");
            return;
        }

        System.out.println("1. Update Balance");
        System.out.println("2. Go back");

        System.out.print("Choice: ");
        String choice = new Scanner(System.in).nextLine();

        switch (choice) {
            case "1" -> {
                System.out.print("Enter new Balance: ");
                int newBalance = Integer.parseInt(new Scanner(System.in).nextLine());
                accountToUpdate.setBalance(newBalance); // Update the balance
            }
            case "2" -> {
                return;
            }
            default -> {
                System.out.println("Invalid choice. No field updated.");
                return;
            }
        }

        accountController.updateAccount(accountToUpdate);
        System.out.println("Account updated successfully.");
    }

    private static void deleteBankAccount(User user) {
        AccountController accountController = new AccountController();
        List<Account> accounts = accountController.getAccounts(user);
        if (accounts.isEmpty()) {
            System.out.println("No bank accounts found.");
            return;
        }

        System.out.print("Enter the account name to delete: ");
        String accountName = new Scanner(System.in).nextLine();

        Account accountToDelete = null;
        for (Account account : accounts) {
            if (account.getAccountName().equals(accountName)) {
                accountToDelete = account;
                break;
            }
        }
        if (accountToDelete == null) {
            System.out.println("Account not found.");
            return;
        }

        System.out.println("Are you sure you want to delete this bank account? (yes/no)");
        String confirmation = new Scanner(System.in).nextLine().toLowerCase();

        if (confirmation.equals("yes") || confirmation.equals("y")) {
            accountController.deleteAccount(accountToDelete);
            System.out.println("Bank account deleted successfully.");
        } else {
            System.out.println("Deletion canceled.");
        }
    }
}

