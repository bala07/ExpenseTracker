package repository;

import com.expenseTracker.domain.Expense;
import com.expenseTracker.repository.ExpenseDao;
import com.expenseTracker.repository.ExpenseDaoImpl;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class ExpenseDaoTests {

    @Mock
    private SessionFactory sessionFactory;
    @Mock
    private Session currentSession;

    private ExpenseDao expenseDao;

    @Before
    public void setup() {
        initMocks(this);
        expenseDao = new ExpenseDaoImpl(sessionFactory);
        when(sessionFactory.getCurrentSession()).thenReturn(currentSession);
    }

    @Test
    public void shouldAddExpense() {
        Expense expense = new Expense();

        expenseDao.addExpense(expense);

        verify(currentSession).save(expense);
    }

    @Test
    public void shouldUpdateExpense() {
        Expense expense = new Expense();

        expenseDao.updateExpense(expense);

        verify(currentSession).update(expense);
    }

    @Test
    public void shouldDeleteExpenseIfPresent() {
        Expense expense = new Expense();
        expense.setId(1);

        when(currentSession.load(Expense.class, 1)).thenReturn(expense);

        expenseDao.deleteExpense(expense);

        verify(currentSession).delete(expense);
    }


    @Test
    public void shouldNotDeleteExpenseIfNotPresent() {
        Expense expense = new Expense();
        expense.setId(1);

        when(currentSession.load(Expense.class, 1)).thenReturn(null);

        expenseDao.deleteExpense(expense);

        verify(currentSession, never()).delete(expense);
    }

    @Test
    public void shouldFindExpenseForGivenId() {
        Expense expectedExpense = new Expense();
        expectedExpense.setId(1);

        Query query = mock(Query.class);

        when(currentSession.createQuery("from Expense e where e.id = :expenseId")).thenReturn(query);
        when(query.setParameter("expenseId", 1)).thenReturn(query);
        when(query.list()).thenReturn(newArrayList(expectedExpense));

        Expense expense = expenseDao.findExpense(1);

        assertThat(expense, is(expectedExpense));
    }

    @Test
    public void shouldReturnNullIfExpenseWithGivenIdNotFound() {
        Query query = mock(Query.class);

        when(currentSession.createQuery("from Expense e where e.id = :expenseId")).thenReturn(query);
        when(query.setParameter("expenseId", 1)).thenReturn(query);
        when(query.list()).thenReturn(newArrayList());

        Expense expense = expenseDao.findExpense(1);

        assertThat(expense, is(nullValue()));
    }

    @Test
    public void shouldFindAllExpenses() {
        Query query = mock(Query.class);
        Expense expense1 = new Expense();
        expense1.setId(1);
        Expense expense2 = new Expense();
        expense2.setId(2);
        List expectedExpenses = newArrayList(expense1, expense2);

        when(currentSession.createQuery("from Expense")).thenReturn(query);
        when(query.list()).thenReturn(expectedExpenses);

        List<Expense> expenses = expenseDao.findAllExpenses();

        assertThat(expenses, is(expectedExpenses));
    }


}
