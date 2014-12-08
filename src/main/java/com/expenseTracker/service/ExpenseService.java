package com.expenseTracker.service;

import com.expenseTracker.domain.Expense;

import java.util.List;

public interface ExpenseService {

    public void addExpense(Expense expense);
    
    public void updateExpense(Expense expense);

    public void deleteExpense(Expense expense);

    public Expense findExpense(int id);

    public List<Expense> getExpensesOfExpenseSheet(Integer expenseSheetId);
}
