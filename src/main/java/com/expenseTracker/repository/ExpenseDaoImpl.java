package com.expenseTracker.repository;

import com.expenseTracker.domain.Expense;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class ExpenseDaoImpl implements ExpenseDao {

    private SessionFactory sessionFactory;

    @Autowired
    public ExpenseDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addExpense(Expense expense) {
        sessionFactory.getCurrentSession().save(expense);
    }

    @Override
    public void updateExpense(Expense expense) {
        sessionFactory.getCurrentSession().update(expense);
    }

    @Override
    public void deleteExpense(Expense expense) {
        Expense expenseFromDb = (Expense) sessionFactory.getCurrentSession().load(Expense.class, expense.getId());
        if (expenseFromDb != null) {
            sessionFactory.getCurrentSession().delete(expenseFromDb);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public Expense findExpense(int id) {
        List<Expense> expenses = sessionFactory
                .getCurrentSession()
                .createQuery("from Expense e where e.id = :expenseId")
                .setParameter("expenseId", id)
                .list();

        return expenses.size() > 0 ? expenses.get(0) : null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Expense> getExpensesOfExpenseSheet(Integer expenseSheetId) {
        return sessionFactory
                .getCurrentSession()
                .createQuery("from Expense e where e.expenseSheet.id = :expenseSheetId")
                .setParameter("expenseSheetId", expenseSheetId)
                .list();
    }
}
