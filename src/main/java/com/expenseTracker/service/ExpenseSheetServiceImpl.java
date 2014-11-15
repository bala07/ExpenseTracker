package com.expenseTracker.service;

import com.expenseTracker.domain.ExpenseSheet;
import com.expenseTracker.repository.ExpenseSheetDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpenseSheetServiceImpl implements ExpenseSheetService {
    @Autowired
    private ExpenseSheetDao expenseSheetDao;

    @Override
    public void addExpenseSheet(ExpenseSheet expenseSheet) {
        expenseSheetDao.addExpenseSheet(expenseSheet);
    }

    @Override
    public void updateExpenseSheet(ExpenseSheet expenseSheet) {
        expenseSheetDao.updateExpenseSheet(expenseSheet);
    }

    @Override
    public void deleteExpenseSheet(ExpenseSheet expenseSheet) {
        expenseSheetDao.deleteExpenseSheet(expenseSheet);
    }

    @Override
    public ExpenseSheet findExpenseSheet(int id) {
        return expenseSheetDao.findExpenseSheet(id);
    }
}
