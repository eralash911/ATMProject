import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class BankAccount {
    public static boolean isLogin = false;

    public static void main(String[] args) throws Exception {
        int option = 0;

        Scanner scanner = new Scanner(System.in);
        while (option == 0){
            System.out.println("\n\nSELECT AN OPTION\n");
            System.out.println("1) Create new account");
            System.out.println("2) Sign in\n");

            while (option < 1 || option > 2){
                System.out.println("Make your choice");
                option = scanner.nextInt();
            }
        }
        switch (option){
            case 1:
                System.out.println("\n\n===Create new account\n");

                System.out.println("Enter your name");
                String firstName = scanner.next().trim();
                System.out.println("Enter your lastName");
                String lastName = scanner.next().trim();

                Account account = new Account(firstName, lastName);
                account.register();

            case 2:
                while (isLogin == false){
                    System.out.println("\n\n===Sign in\n");
                    System.out.println("Enter your card number");

                    String cardNumber = scanner.next();
                    System.out.println("Enter your pincode");
                    String pincode = scanner.next();
                    Operations operations = new Operations(cardNumber, pincode);

                    try {

                        Connection connection = Database.connection();
                        Statement statement3 = connection.createStatement();

                        String sql3 = "SELECT * FROM Card WHERE card_number = '" + cardNumber + "' AND pincode '" + pincode + "'";

                        ResultSet resultSet3 = statement3.executeQuery(sql3);
                        if (resultSet3.next()){
                            isLogin = true;

                            System.out.println("\n\n =====login success");

                            System.out.println("----Enter an option----\n");
                            System.out.println("1.Balance");
                            System.out.println("2.Deposit");
                            System.out.println("3.Send money");

                            int option_user = 0;

                            while (option_user < 0 || option_user > 3){
                                System.out.println("\n make your choose");

                                option_user = scanner.nextInt();
                            }
                            int balance = 0;

                            switch (option_user){
                                case 1:
                                System.out.println("\n\n===SHOW MONEY\n");
                                balance = operations.showBalance(cardNumber);
                                    System.out.println(balance + " $");
                                    break;
                                case 2:
                                    System.out.println("\n\n===MAKE DEPOSIT\n");
                                    int amount = 0;
                                    while (amount <= 0){
                                        System.out.println("---Type amount: ");
                                        amount = scanner.nextInt();

                                    }
                                    operations.deposit(amount, cardNumber);
                                    balance = operations.showBalance(cardNumber);
                                    System.out.println("\nCurrent balance: " + balance + " $");
                                    break;
                                case 3:
                                    System.out.println("\n\n ===SEND MONEY\n");
                                    System.out.println("Enter number other client");
                                    int number_other = scanner.nextInt();
                                    int amount_other = 0;

                                    while (amount_other <= 0 ){
                                        System.out.println("Enter amount to other client");
                                        amount_other = scanner.nextInt();
                                    }
                                    operations.sendMoneyToOther(amount_other, "number_other", cardNumber);
                                    System.out.println("\n You sent " + amount_other + " $ to " + number_other);
                                    break;

                                default:
                                    break;

                            }
                        }else {
                            System.out.println("log in fail");
                        }

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
        }
    }
}
