package com.expenseTracker.service;

import com.expenseTracker.domain.Expense;
import com.expenseTracker.repository.ExpenseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpenseDao expenseDao;

    @Override
    public void addExpense(Expense expense) {
        expenseDao.addExpense(expense);
    }

    @Override
    public void updateExpense(Expense expense) {
        expenseDao.updateExpense(expense);
    }

    @Override
    public void deleteExpense(Expense expense) {
        expenseDao.deleteExpense(expense);
    }

    @Override
    public Expense findExpense(int id) {
        return expenseDao.findExpense(id);
    }

    @Override
    public List<Expense> findAllExpenses() {
        return expenseDao.findAllExpenses();
    }
}
