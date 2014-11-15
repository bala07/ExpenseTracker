package com.expenseTracker.repository;

import com.expenseTracker.domain.ExpenseSheet;

public interface ExpenseSheetDao {
    void addExpenseSheet(ExpenseSheet expenseSheet);

    void updateExpenseSheet(ExpenseSheet expenseSheet);

    void deleteExpenseSheet(ExpenseSheet expenseSheet);

    ExpenseSheet findExpenseSheet(int id);
}
