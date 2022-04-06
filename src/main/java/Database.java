import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private final static String dbClassName = "com.mysql.cj.jdbc.Driver";
    private final static String CONNECTION = "jdbc:mysql://localhost:3306/ATMConnection";
    private final static String USER = "root";
    private final static String PASSWORD = "";
    public static java.sql.Connection connection() throws ClassNotFoundException, SQLException {
        Class.forName(dbClassName);
        return DriverManager.getConnection(CONNECTION, USER, PASSWORD);
    }
}
