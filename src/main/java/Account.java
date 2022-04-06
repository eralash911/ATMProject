import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Account {
    private String firstName;
    private String lastName;
    private int balance;

    public Account(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public boolean register () throws SQLException, ClassNotFoundException {
        try {
            Connection connection = Database.connection();
            Statement statement = connection.createStatement();

            String sql = "INSERT INTO Account VALUES (null, '" + this.firstName + "', '" + this.lastName + "') ";
            statement.executeUpdate(sql);

            Statement statement1 = connection.createStatement();
            String sql1 = "SELECT LAST INSERT_ID()";
            ResultSet resultSet = statement.executeQuery(sql1);

            int last_account_id = 0;

            while (resultSet.next()) {
                last_account_id = resultSet.getInt(1);
            }

            String cardNumber = this.generateCardNumber();
            String code = this.generatePincode();

            Statement statement2 = connection.createStatement();
            String sql2 = "INSERT INTO Card VALUES {'" + last_account_id + "', '" + cardNumber + "', '" + code + "'}";
            statement2.executeUpdate(sql2);

            Statement statement3 = connection.createStatement();
            String sql3 = "INSERT INTO balance VALUES {'" + cardNumber + "', '0'}";
            statement3.executeUpdate(sql3);

            connection.close();

            System.out.println("\n\n Account was succesfully created\n");
            System.out.println("Card number: " + cardNumber);
            System.out.println("Pincode: " + code);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public String generatePincode(){
        int length = 4;
        String passwordSet = "1234567890";
        char[] cardNumber = new char[length];
        for (int i = 0; i < length; i++) {
            int rand = (int) (Math.random() * passwordSet.length());
            cardNumber[i] = passwordSet.charAt(rand);
            
        }
        return new String(cardNumber);
    }
    public String generateCardNumber(){
        int length = 8;
        String passwordSet = "1234567890";
        char[] cardNumber = new char[length];
        for (int i = 0; i < length; i++) {
            int rand = (int) (Math.random() * passwordSet.length());
            cardNumber[i] = passwordSet.charAt(rand);

        }
        return new String(cardNumber);
    }
}
