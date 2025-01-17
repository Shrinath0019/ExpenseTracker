package com.expense.service;

import com.expense.dao.ExpenseDAO;
import com.expense.model.Expense;
import java.sql.SQLException;
import java.util.ArrayList;  // Add this import
import java.util.List;

public class ExpenseService {
    private ExpenseDAO expenseDAO = new ExpenseDAO();

    public void addExpense(Expense expense) {
        try {
            expenseDAO.addExpense(expense);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Expense> getAllExpenses() {
        try {
            return expenseDAO.getAllExpenses();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
