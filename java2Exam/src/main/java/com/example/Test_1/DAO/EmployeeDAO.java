package com.example.Test_1.DAO;
import com.example.Test_1.model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    private final String url = "jdbc:mysql://localhost:3306/EmployeeDB"; // URL kết nối đến MySQL
    private final String user = "root";  // Tên người dùng MySQL
    private final String password = "";

    private Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    // Tạo bảng (không cần thiết nếu đã tạo sẵn qua MySQL Workbench)
    public void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS employee ("
                + " id INT AUTO_INCREMENT PRIMARY KEY,"
                + " name VARCHAR(100) NOT NULL,"
                + " position VARCHAR(100) NOT NULL,"
                + " salary DECIMAL(10, 2) NOT NULL"
                + ");";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Thêm nhân viên
    public void addEmployee(Employee emp) {
        String sql = "INSERT INTO employee(name, position, salary) VALUES(?, ?, ?)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, emp.getName());
            pstmt.setString(2, emp.getPosition());
            pstmt.setDouble(3, emp.getSalary());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Lấy danh sách tất cả nhân viên
    public List<Employee> getAllEmployees() {
        String sql = "SELECT * FROM employee";
        List<Employee> employees = new ArrayList<>();
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                employees.add(new Employee(rs.getInt("id"), rs.getString("name"),
                        rs.getString("position"), rs.getDouble("salary")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return employees;
    }


    public void updateEmployee(Employee emp) {
        String sql = "UPDATE employee SET name = ?, position = ?, salary = ? WHERE id = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, emp.getName());
            pstmt.setString(2, emp.getPosition());
            pstmt.setDouble(3, emp.getSalary());
            pstmt.setInt(4, emp.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Xóa nhân viên
    public void deleteEmployee(int id) {
        String sql = "DELETE FROM employee WHERE id = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

