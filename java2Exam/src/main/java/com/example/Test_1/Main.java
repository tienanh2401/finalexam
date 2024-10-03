package com.example.Test_1;
import java.sql.*;
import java.util.Scanner;

public class Main {
    // MySQL database credentials
    private static final String DB_URL = "jdbc:mysql://localhost:3306/employeedb";
    private static final String USER = "root";
    private static final String PASS = "";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("\nCRUD Operations:");
                System.out.println("1. Create (Add Employee)");
                System.out.println("2. Delete (Remove Employee by ID)");
                System.out.println("3. Update (Edit Employee by ID)");
                System.out.println("4. Print All Employees");
                System.out.println("0. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Clear buffer

                switch (choice) {
                    case 1:
                        addEmployee(conn, scanner);
                        break;
                    case 2:
                        deleteEmployeeById(conn, scanner);
                        break;
                    case 3:
                        updateEmployeeById(conn, scanner);
                        break;
                    case 4:
                        printAllEmployees(conn);
                        break;
                    case 0:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Create - Add a new employee
    private static void addEmployee(Connection conn, Scanner scanner) {
        try {
            System.out.print("Enter employee first_name: ");
            String first_name = scanner.nextLine();
            System.out.print("Enter employee last_name: ");
            String last_name = scanner.nextLine();
            scanner.nextLine(); // Clear buffer
            System.out.print("Enter employee email: ");
            String email = scanner.nextLine();

            String sql = "INSERT INTO employee (first_name,last_name,email) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, first_name);
                pstmt.setString(2, last_name);
                pstmt.setString(3, email);
                pstmt.executeUpdate();
                System.out.println("Employee added successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete - Remove an employee by ID
    private static void deleteEmployeeById(Connection conn, Scanner scanner) {
        try {
            System.out.print("Enter employee ID to delete: ");
            int id = scanner.nextInt();

            String sql = "DELETE FROM employee WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, id);
                int rowsDeleted = pstmt.executeUpdate();
                if (rowsDeleted > 0) {
                    System.out.println("Employee deleted successfully.");
                } else {
                    System.out.println("Employee with ID " + id + " not found.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update - Edit an employee by ID
    private static void updateEmployeeById(Connection conn, Scanner scanner) {
        try {
            System.out.print("Enter employee ID to update: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            System.out.print("Enter new first_name: ");
            String first_name = scanner.nextLine();
            System.out.print("Enter new last_name: ");
            String last_name = scanner.nextLine();
            scanner.nextLine(); // Clear buffer
            System.out.print("Enter new email: ");
            String position = scanner.nextLine();

            String sql = "UPDATE employee SET first_name = ?, last_name = ?, email = ? WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, first_name);
                pstmt.setString(2, last_name);
                pstmt.setString(3, position);
                pstmt.setInt(4, id);
                int rowsUpdated = pstmt.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Employee updated successfully.");
                } else {
                    System.out.println("Employee with ID " + id + " not found.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Read - Print all employees
    private static void printAllEmployees(Connection conn) {
        try {
            String sql = "SELECT * FROM employee";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {
                System.out.println("\nEmployee List:");
                while (rs.next()) {
                    System.out.printf("ID: %d, first_name: %s, last_name: %s, email: %s%n",
                            rs.getInt("id"), rs.getString("first_name"),
                            rs.getString("last_name"), rs.getString("email"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

