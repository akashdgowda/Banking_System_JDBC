# Banking_System_JDBC
This is a simple Banking System implemented in Java using JDBC to interact with a MySQL database.

## Prerequisites
- JDK installed
- MySQL server installed and running
- MySQL Connector/J library in the classpath

## Database Setup
1. Create a MySQL database named `banking_system`.
2. Update the database connection details in `Main.java`:
     ```sql
   CREATE TABLE accounts (
       account_number bigint PRIMARY KEY,
       full_name varchar(255) NOT NULL,
       email varchar(255) UNIQUE NOT NULL,
       balance decimal(10,2) NOT NULL,
       security_pin char(4) NOT NULL
   );

   CREATE TABLE user (
       full_name varchar(255) NOT NULL,
       email varchar(255) PRIMARY KEY,
       password varchar(255) NOT NULL
   );
   ```

### Update the database connection details in Main.java:     
   ```java
   public static final String url = "jdbc:mysql://localhost:3306/banking_system";
   public static final String username = "root";
   public static final String password = "MySQLPassword";
```

## Features
> Register: Create a new user account.
> Login: Log in with an existing user account.
> Open Bank Account: Create a new bank account associated with the logged-in user.
> Debit Money: Withdraw money from the user's bank account.
> Credit Money: Deposit money into the user's bank account.
> Transfer Money: Transfer money between bank accounts.
> Check Balance: View the current balance of the user's bank account.
> Logout: Log out from the system.
   
Feel free to adapt the database schema according to your requirements.

## Acknowledgment
This project uses the MySQL database for data storage.
Special thanks to the [MySQL](https://www.mysql.com/) and [Java](https://www.oracle.com/java/) communities.
   
