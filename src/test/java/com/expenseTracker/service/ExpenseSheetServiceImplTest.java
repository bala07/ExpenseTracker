package com.expenseTracker.service;

import com.expenseTracker.domain.ExpenseSheet;
import com.expenseTracker.repository.ExpenseSheetDao;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ExpenseSheetServiceImplTest {
    private ExpenseSheetService expenseSheetService;
    @Mock
    private ExpenseSheetDao expenseSheetDao;

    @BeforeMethod
    public void setup() {
        initMocks(this);
        expenseSheetService = new ExpenseSheetServiceImpl(expenseSheetDao);
    }

    @Test
    public void shouldAddExpenseSheet() {
        ExpenseSheet expenseSheet = new ExpenseSheet();

        expenseSheetService.addExpenseSheet(expenseSheet);

        verify(expenseSheetDao).addExpenseSheet(expenseSheet);
    }

    @Test
    public void shouldUpdateExpenseSheet() {
        ExpenseSheet expenseSheet = new ExpenseSheet();

        expenseSheetService.updateExpenseSheet(expenseSheet);

        verify(expenseSheetDao).updateExpenseSheet(expenseSheet);
    }

    @Test
    public void shouldDeleteExpenseSheet() {
        ExpenseSheet expenseSheet = new ExpenseSheet();

        expenseSheetService.deleteExpenseSheet(expenseSheet);

        verify(expenseSheetDao).deleteExpenseSheet(expenseSheet);
    }

    @Test
    public void shouldFindExpenseSheetById() {
        ExpenseSheet expectedExpenseSheet = new ExpenseSheet();

        when(expenseSheetDao.findExpenseSheet(1)).thenReturn(expectedExpenseSheet);

        ExpenseSheet expenseSheet = expenseSheetService.getExpenseSheet(1);

        assertThat(expenseSheet, is(expectedExpenseSheet));
    }
}