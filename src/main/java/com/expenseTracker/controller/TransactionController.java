package com.expenseTracker.controller;

import com.expenseTracker.domain.Transaction;
import com.expenseTracker.service.TransactionService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    private final Logger logger = Logger.getLogger(TransactionController.class);

    @RequestMapping("/transactions")
    public ModelAndView transactionList() {
        List<Transaction> transactions = transactionService.findAllTransactions();
        Map<String, List<Transaction>> transactionListModel = new HashMap<String, List<Transaction>>();
        transactionListModel.put("transactions", transactions);

        logger.info("Number of transactions => " + transactions.size());
        logger.info("Transaction id => " + transactions.get(0).getId());

        return new ModelAndView("transactions", transactionListModel);
    }
}
