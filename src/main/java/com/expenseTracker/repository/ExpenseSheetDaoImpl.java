package com.expenseTracker.repository;

import com.expenseTracker.domain.ExpenseSheet;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class ExpenseSheetDaoImpl implements ExpenseSheetDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addExpenseSheet(ExpenseSheet expenseSheet) {
        sessionFactory.getCurrentSession().save(expenseSheet);
    }

    @Override
    public void updateExpenseSheet(ExpenseSheet expenseSheet) {
        sessionFactory.getCurrentSession().update(expenseSheet);
    }

    @Override
    public void deleteExpenseSheet(ExpenseSheet expenseSheet) {
        ExpenseSheet expenseSheetFromDb = (ExpenseSheet) sessionFactory.getCurrentSession().load(ExpenseSheet.class, expenseSheet.getId());
        if (expenseSheetFromDb != null) {
            sessionFactory.getCurrentSession().delete(expenseSheet);
        }
    }

    @Override
    public ExpenseSheet findExpenseSheet(int id) {
        List<ExpenseSheet> expenseSheet = sessionFactory
                .getCurrentSession()
                .createQuery("from ExpenseSheet where id = :expenseId")
                .setParameter("expenseId", id)
                .list();

        return expenseSheet.size() > 0 ? expenseSheet.get(0) : null;
    }
}
