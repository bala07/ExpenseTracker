package com.expenseTracker.service;

import com.expenseTracker.domain.Transaction;

import java.util.List;

public interface TransactionService {

    public void addTransaction(Transaction transaction);
    
    public void updateTransaction(Transaction transaction);

    public void deleteTransaction(Transaction transaction);

    public Transaction findTransaction(int id);

    public List<Transaction> findAllTransactions();
}
