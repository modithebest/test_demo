package Project;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class bank {
    public static void main(String[] args) {
        System.out.println("Welcome to MyBank");
        Scanner scanner = new Scanner(System.in);
        ArrayList<Map<String, Object>> credentialsList = new ArrayList<>();

        while (true) {

            String printl = """
                    1.) Click 1 to Create an Account
                    2.) Click 2 to Deposit Funds
                    3.) Click 3 to withdraw Funds
                    4.) Exit
                    """;
            System.out.println(printl);
            System.out.println("Enter your choice: ");
            String choice = scanner.next();

            if ("1".equals(choice)) {
                System.out.println("Enter Account number: ");
                String accountNumber = scanner.next();

                System.out.println("Enter a Password: ");
                String password = scanner.next();

                System.out.println("Confirm password: ");
                String confirmPassword = scanner.next();

                System.out.println("Initial Funds Deposits: ");
                double initialFunds = scanner.nextDouble();

                boolean flag = true;

                System.out.println("Password length: " + password.length());

                if (accountNumber.length() != 8) {
                    System.out.println("Account number must be 8 digits long.");
                    flag = false;
                }

                if (password.length() < 8) {
                    System.out.println("Password must be 8 characters long.");
                    flag = false;
                }

                if (!password.equals(confirmPassword)) {
                    System.out.println("Passwords do not match.");
                    flag = false;
                }

                if (initialFunds <= 100.00) {
                    System.out.println("Initial funds should be more than $100.00.");
                    flag = false;
                }

                if (flag) {
                    Map<String, Object> credentials = new HashMap<>();
                    credentials.put("Account Number", accountNumber);
                    credentials.put("Password", password);
                    credentials.put("Balance", initialFunds);
                    credentialsList.add(credentials);
                    System.out.println("Your account has been created successfully!");

                }
            } else if ("2".equals(choice)) {
                int attempts = 0;
                boolean isAuthenticated = false;

                while (attempts < 3 && !isAuthenticated) {

                    System.out.println("Enter Account number: ");
                    String accountNumber = scanner.next();

                    System.out.println("Enter a Password: ");
                    String password = scanner.next();

                    if (accountNumber.length() != 8) {
                        System.out.println("Account number must be 8 digits long.");
                        continue;

                    }
                    if (password.length() < 8) {
                        System.out.println("Password must be 8 characters long.");
                        continue;

                    }
                    for (Map<String, Object> credentials : credentialsList) {
                        String storedAccountNumber = (String) credentials.get("Account Number");
                        String storedPassword = (String) credentials.get("Password");
                        double funds = (double) credentials.get("Balance");

                        if (storedAccountNumber != null && storedAccountNumber.equals(accountNumber)
                                && storedPassword != null && storedPassword.equals(password)) {
                            isAuthenticated = true;
                            System.out.println("Enter the amount you want to deposit:  ");
                            double amountDeposit = scanner.nextDouble();
                            double totalFunds = funds + amountDeposit;
                            credentials.put("Balance", totalFunds);
                            System.out.println(
                                    "Your Funds has been Deposited Your Balance: " + String.valueOf(totalFunds));

                            break;

                        }

                    }
                    if (!isAuthenticated) {
                        System.out.println("Incorrect account number or password.");
                        attempts++;
                    }

                }
                if (attempts == 3) {
                    System.out.println("Your account has been blocked");
                    break;
                }

            } else if ("3".equals(choice)) {
                int attempts = 0;
                boolean isAuthenticated = false;
                while (true) {
                    if (attempts >= 3) {
                        System.out.println("Your account has been blocked");
                        break;

                    }
                    System.out.println("Enter Account number: ");
                    String AccountNumber = scanner.next();
                    System.out.println("Enter your Password:  ");
                    String Password = scanner.next();

                    if (AccountNumber.length() != 8) {
                        System.out.println("Account number must have a length of 8");
                        continue;
                    }
                    if (Password.length() < 8) {
                        System.out.println("The password must have length equals to or greater than 8.");
                        continue;
                    }

                    for (Map<String, Object> credentials : credentialsList) {
                        String storedAccountNumber = (String) credentials.get("Account Number");
                        String storedPassword = (String) credentials.get("Password");
                        double funds = (double) credentials.get("Balance");
                        if (AccountNumber.equals(storedAccountNumber) && Password.equals(storedPassword)) {
                            isAuthenticated = true;
                            System.out.println("Enter the amount you want to withdraw:  ");
                            double withdraw = scanner.nextDouble();
                            scanner.nextLine();
                            if (withdraw > funds) {
                                System.out.println(
                                        "Your Withdrawal amount is currently higher than your account balance.");
                                continue;
                            }
                            double totalFunds = funds - withdraw;
                            System.out.println("Balance Left: " + String.valueOf(totalFunds));
                            isAuthenticated = true;
                            break;

                        }
                    }
                    if (!isAuthenticated) {
                        System.out.println("Incorrect account number or password.");
                        attempts++;
                    }

                }

            } else if ("4".equals(choice)) {
                break;
            }
            System.out.println(credentialsList);

        }
    }
}
