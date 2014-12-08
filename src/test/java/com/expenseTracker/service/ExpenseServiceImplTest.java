package com.expenseTracker.service;

import com.expenseTracker.domain.Expense;
import com.expenseTracker.repository.ExpenseDao;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ExpenseServiceImplTest {
    private ExpenseService expenseService;
    @Mock
    private ExpenseDao expenseDao;

    @BeforeMethod
    public void setup() {
        initMocks(this);
        expenseService = new ExpenseServiceImpl(expenseDao);
    }

    @Test
    public void shouldAddExpense() {
        Expense expense = new Expense();

        expenseService.addExpense(expense);

        verify(expenseDao).addExpense(expense);
    }

    @Test
    public void shouldUpdateExpense() {
        Expense expense = new Expense();

        expenseService.updateExpense(expense);

        verify(expenseDao).updateExpense(expense);
    }

    @Test
    public void shouldDeleteExpense() {
        Expense expense = new Expense();

        expenseService.deleteExpense(expense);

        verify(expenseDao).deleteExpense(expense);
    }

    @Test
    public void shouldFindExpenseById() {
        Expense expectedExpense = new Expense();

        when(expenseDao.findExpense(1)).thenReturn(expectedExpense);

        Expense expense = expenseService.findExpense(1);

        assertThat(expense, is(expectedExpense));
    }

    @Test
    public void shouldFindAllExpenses() {
        Integer expenseSheetId = 1;
        List<Expense> expectedExpenses = newArrayList();

        when(expenseDao.getExpensesOfExpenseSheet(expenseSheetId)).thenReturn(expectedExpenses);

        List<Expense> expenses = expenseService.getExpensesOfExpenseSheet(expenseSheetId);

        assertThat(expenses, is(expectedExpenses));
    }
}