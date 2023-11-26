import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static final String url = "jdbc:mysql://localhost:3306/banking_system";
    public static final String username = "root";
    public static final String password = "MySQLPassword";
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        try{
            Connection connection = DriverManager.getConnection(url, username, password);
            Scanner scanner =new Scanner(System.in);
            User user = new User(connection, scanner);
            Accounts account = new Accounts(connection, scanner);
            AccountManager accountManager = new AccountManager(connection, scanner);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
}