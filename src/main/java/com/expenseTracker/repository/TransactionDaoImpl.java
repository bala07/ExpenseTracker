package com.expenseTracker.repository;

import com.expenseTracker.domain.Transaction;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class TransactionDaoImpl implements TransactionDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addTransaction(Transaction transaction) {
        sessionFactory.getCurrentSession().save(transaction);
    }

    @Override
    public void updateTransaction(Transaction transaction) {
        sessionFactory.getCurrentSession().update(transaction);
    }

    @Override
    public void deleteTransaction(Transaction transaction) {
        Transaction transactionFromDb = (Transaction) sessionFactory.getCurrentSession().load(Transaction.class, transaction.getId());
        if (transactionFromDb != null) {
            sessionFactory.getCurrentSession().delete(transactionFromDb);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public Transaction findTransaction(int id) {
        List<Transaction> transactions = sessionFactory
                .getCurrentSession()
                .createQuery("from Transaction t where t.id = :transactionId")
                .setParameter("transactionId", id)
                .list();

        return transactions.size() > 0 ? transactions.get(0) : null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Transaction> findAllTransactions() {
        return sessionFactory
                .getCurrentSession()
                .createQuery("from Transaction")
                .list();
    }
}
