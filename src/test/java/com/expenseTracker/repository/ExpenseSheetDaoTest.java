package com.expenseTracker.repository;

import com.expenseTracker.domain.ExpenseSheet;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class ExpenseSheetDaoTest {
    private ExpenseSheetDao expenseSheetDao;

    @Mock
    SessionFactory sessionFactory;
    @Mock
    Session currentSession;

    @BeforeMethod
    public void setup() {
        initMocks(this);
        expenseSheetDao = new ExpenseSheetDaoImpl(sessionFactory);
        when(sessionFactory.getCurrentSession()).thenReturn(currentSession);
    }

    @Test
    public void shouldAddExpenseSheet() {
        ExpenseSheet expenseSheet = new ExpenseSheet();

        expenseSheetDao.addExpenseSheet(expenseSheet);

        verify(currentSession).save(expenseSheet);
    }

    @Test
    public void shouldUpdateExpenseSheet() {
        ExpenseSheet expenseSheet = new ExpenseSheet();

        expenseSheetDao.updateExpenseSheet(expenseSheet);

        verify(currentSession).update(expenseSheet);
    }

    @Test
    public void shouldDeleteExpenseSheetIfPresent() {
        ExpenseSheet expenseSheet = new ExpenseSheet();
        expenseSheet.setId(1);

        when(currentSession.load(ExpenseSheet.class, 1)).thenReturn(expenseSheet);

        expenseSheetDao.deleteExpenseSheet(expenseSheet);

        verify(currentSession).delete(expenseSheet);
    }

    @Test
    public void shouldNotDeleteExpenseSheetIfNotPresent() {
        ExpenseSheet expenseSheet = new ExpenseSheet();

        when(currentSession.load(ExpenseSheet.class, 1)).thenReturn(null);

        expenseSheetDao.deleteExpenseSheet(expenseSheet);

        verify(currentSession, never()).delete(expenseSheet);
    }

    @Test
    public void shouldFindExpenseSheetById() {
        Query query = mock(Query.class);
        ExpenseSheet expectedExpenseSheet = new ExpenseSheet();

        when(currentSession.createQuery("from ExpenseSheet e where e.id = :expenseSheetId")).thenReturn(query);
        when(query.setParameter("expenseSheetId", 1)).thenReturn(query);
        when(query.list()).thenReturn(newArrayList(expectedExpenseSheet));

        ExpenseSheet expenseSheet = expenseSheetDao.findExpenseSheet(1);

        assertThat(expenseSheet, is(expectedExpenseSheet));
    }

    @Test
    public void shouldReturnNullIfExpenseSheetWithGivenIdNotFound() {
        Query query = mock(Query.class);

        when(currentSession.createQuery("from ExpenseSheet e where e.id = :expenseSheetId")).thenReturn(query);
        when(query.setParameter(anyString(), anyInt())).thenReturn(query);
        when(query.list()).thenReturn(newArrayList());

        ExpenseSheet expenseSheet = expenseSheetDao.findExpenseSheet(1);

        assertThat(expenseSheet, is(nullValue()));
    }
}
