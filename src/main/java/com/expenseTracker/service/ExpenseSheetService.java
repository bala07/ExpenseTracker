package com.expenseTracker.service;

import com.expenseTracker.domain.ExpenseSheet;

public interface ExpenseSheetService {
    void addExpenseSheet(ExpenseSheet expenseSheet);

    void updateExpenseSheet(ExpenseSheet expenseSheet);

    void deleteExpenseSheet(ExpenseSheet expenseSheet);

    ExpenseSheet findExpenseSheet(int id);
}
