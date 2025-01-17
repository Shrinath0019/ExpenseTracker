package com.expense.dao;

import java.sql.*;
import com.expense.model.Expense;
import java.util.ArrayList;
import java.util.List;

public class ExpenseDAO {
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection connect() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/ExpenseDB";
        String user = "root";
        String password = "yourpassword";
        return DriverManager.getConnection(url, user, password);
    }

    public void addExpense(Expense expense) throws SQLException {
        String query = "INSERT INTO Expenses (date, amount, category, description) VALUES (?, ?, ?, ?)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setDate(1, expense.getDate());
            pstmt.setBigDecimal(2, expense.getAmount());
            pstmt.setString(3, expense.getCategory());
            pstmt.setString(4, expense.getDescription());
            pstmt.executeUpdate();
        }
    }

    public List<Expense> getAllExpenses() throws SQLException {
        List<Expense> expenses = new ArrayList<>();
        String query = "SELECT * FROM Expenses";
        try (Connection conn = connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Expense expense = new Expense();
                expense.setId(rs.getInt("id"));
                expense.setDate(rs.getDate("date"));
                expense.setAmount(rs.getBigDecimal("amount"));
                expense.setCategory(rs.getString("category"));
                expense.setDescription(rs.getString("description"));
                expenses.add(expense);
            }
        }
        return expenses;
    }
}
