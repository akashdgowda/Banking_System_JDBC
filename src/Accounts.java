import java.sql.*;
import java.util.Scanner;

public class Accounts {
    Connection connection;
    Scanner scanner;

    public Accounts(Connection connection, Scanner scanner) {
        this.connection = connection;
        this.scanner = scanner;
    }

    public long open_account(String email){
        if(!account_exits(email)){
            String openAccountQuery = "INSERT INTO accounts(account_number, full_name, email, balance, security_pin)" +
                    " VALUES(?,?,?,?)";
            scanner.nextLine();
            System.out.println("Enter Full Name: ");
            String fullName = scanner.nextLine();
            System.out.println("Enter initial Deposit: ");
            double balance = scanner.nextDouble();
            scanner.nextLine();
            System.out.println("Enter Security pin: ");
            String pin = scanner.nextLine();
            try{
                long accountNumber = generateAccountNumber();
                PreparedStatement preparedStatement = connection.prepareStatement(openAccountQuery);
                preparedStatement.setLong(1, accountNumber);
                preparedStatement.setString(2,fullName);
                preparedStatement.setString(3,email);
                preparedStatement.setDouble(4,balance);
                preparedStatement.setString(5,pin);
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Account created Successfully");
                    return accountNumber;
                }else{
                    throw new RuntimeException("Account Creation Failed");
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        throw new RuntimeException("Account Already Exists");
    }
    public long generateAccountNumber(){
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT account_number FROM accounts ORDER BY account_number DESC LIMIT 1";
            ResultSet resultSet = statement.executeQuery(query);
            if(resultSet.next()){
                long recent_account_number = resultSet.getLong("account_number");
                return recent_account_number+1;
            }else{
                return 10000100;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());;
        }
        return 10000100;
    }

    public boolean account_exits(String email){
        String query = "SELECT account_number FROM accounts WHERE email = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return true;
            }else{
                return false;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
