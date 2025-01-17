package com.expense.ui;

import com.expense.service.ExpenseService;
import com.expense.model.Expense;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Scanner;

public class MainUI {
    public static void main(String[] args) {
        ExpenseService expenseService = new ExpenseService();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add Expense\n2. View All Expenses\n3. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter date (yyyy-mm-dd): ");
                    Date date = Date.valueOf(scanner.nextLine());
                    System.out.print("Enter amount: ");
                    BigDecimal amount = scanner.nextBigDecimal();
                    scanner.nextLine(); // consume newline
                    System.out.print("Enter category: ");
                    String category = scanner.nextLine();
                    System.out.print("Enter description: ");
                    String description = scanner.nextLine();

                    Expense expense = new Expense(date, amount, category, description);
                    expenseService.addExpense(expense);
                    break;

                case 2:
                    expenseService.getAllExpenses().forEach(System.out::println);
                    break;

                case 3:
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
