package com.triesten.framework.validate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcExample {

    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/mydatabase";
    private static final String USERNAME = "your_username";
    private static final String PASSWORD = "your_password";

    // JDBC variables for opening, closing, and managing connection
    private static Connection connection;

    public static void main(String[] args) {
        try {
            // Connect to MySQL database
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            // Insert data into table
            insertData(101,"Jenny", 25, 10000);
            insertData(102,"Jacky", 30, 20000);
            insertData(103,"Joe", 20, 40000);
            insertData(104,"John", 40, 80000);
            insertData(105,"Shameer", 25, 90000);

            System.out.println("Data inserted successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Method to insert data into the table
    private static void insertData(Integer empcode,String name, int age, double salary) throws SQLException {
        String sql = "INSERT INTO empl (empcode,empname, empage, empsalary) VALUES (?, ?, ?,?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, empcode);
            statement.setString(2, name);
            statement.setInt(3, age);
            statement.setDouble(4, salary);

            statement.executeUpdate();
        }
    }
}

