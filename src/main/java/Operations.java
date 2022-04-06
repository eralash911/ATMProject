import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Operations {
    private Integer balance;
    private String pincode;
    private String cardNumber;

    public Operations(String pincode, String cardNumber) {
        this.pincode = pincode;
        this.cardNumber = cardNumber;
    }

    public Integer showBalance(String cardNumber)  {
        try{
            Connection c = Database.connection();
            Statement statement4 = c.createStatement();

            String sql4 = "SELECT FROM Balance WHERE card_number = '" + cardNumber + "'";
            ResultSet resultSet4 = statement4.executeQuery(sql4);
            while (resultSet4.next()){
                this.balance = resultSet4.getInt(2);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return this.balance;
    }
    public void deposit(Integer amount, String cardNumber){
        try{
            Connection c = Database.connection();
            Statement statement5 = c.createStatement();

            String sql5 = "UPDATE Balance SET balance = balance + '" + amount + "' WHERE card_number = '" + cardNumber + "'";
            statement5.executeUpdate(sql5);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void sendMoneyToOther(Integer amount_other, String number_other, String cardNumber){
        try {
            Connection c = Database.connection();
            Statement statement6  = c.createStatement();
            String sql6 = "UPDATE Balance SET balance = balance + '" + amount_other + "' WHERE cart_number = '" + number_other + "'";
            statement6.executeUpdate(sql6);

            Statement statement7 = c.createStatement();
            String sql7 = "UPDATE Balance SET balance = balance - '" + amount_other + "' WHERE cart_number = '" + number_other + "'";
            statement7.executeUpdate(sql7);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
