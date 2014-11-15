package com.expenseTracker.service;

import com.expenseTracker.domain.Transaction;
import com.expenseTracker.repository.TransactionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionDao transactionDao;

    @Override
    public void addTransaction(Transaction transaction) {
        transactionDao.addTransaction(transaction);
    }

    @Override
    public void updateTransaction(Transaction transaction) {
        transactionDao.updateTransaction(transaction);
    }

    @Override
    public void deleteTransaction(Transaction transaction) {
        transactionDao.deleteTransaction(transaction);
    }

    @Override
    public Transaction findTransaction(int id) {
        return transactionDao.findTransaction(id);
    }

    @Override
    public List<Transaction> findAllTransactions() {
        return transactionDao.findAllTransactions();
    }
}
