package com.expenseTracker.controller;

import com.expenseTracker.domain.Expense;
import com.expenseTracker.service.ExpenseService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;

    private final Logger logger = Logger.getLogger(ExpenseController.class);

    @RequestMapping("/expenses")
    public ModelAndView expenseList() {
        List<Expense> expenses = expenseService.findAllExpenses();
        Map<String, List<Expense>> expenseListModel = new HashMap<String, List<Expense>>();
        expenseListModel.put("expenses", expenses);

        logger.info("Number of expenses => " + expenses.size());
        logger.info("Expense id => " + expenses.get(0).getId());

        return new ModelAndView("expenses", expenseListModel);
    }
}
