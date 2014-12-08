package com.expenseTracker.controller;

import com.expenseTracker.domain.Expense;
import com.expenseTracker.service.ExpenseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ExpenseController {
    private ExpenseService expenseService;

    @Autowired
    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    private final Logger logger = LoggerFactory.getLogger(ExpenseController.class);

    @RequestMapping("{expenseSheetId}/expenses")
    public ModelAndView expenseList(@PathVariable final Integer expenseSheetId) {
        List<Expense> expenses = expenseService.getExpensesOfExpenseSheet(expenseSheetId);
        Map<String, List<Expense>> expenseListModel = new HashMap<String, List<Expense>>();
        expenseListModel.put("expenses", expenses);

        logger.info("Number of expenses => " + expenses.size());

        return new ModelAndView("expense_list", expenseListModel);
    }
}
