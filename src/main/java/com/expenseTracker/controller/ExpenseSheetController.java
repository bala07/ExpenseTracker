package com.expenseTracker.controller;

import com.expenseTracker.domain.ExpenseSheet;
import com.expenseTracker.service.ExpenseSheetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

@Controller
public class ExpenseSheetController {
    private final Logger LOGGER = LoggerFactory.getLogger(ExpenseSheetController.class);

    @Autowired
    private ExpenseSheetService expenseSheetService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/expense-sheet/create-expense-sheet")
    public ModelAndView createExpenseSheet() {
        ExpenseSheet expenseSheet = new ExpenseSheet();
        expenseSheetService.addExpenseSheet(expenseSheet);

        LOGGER.info("Created new expense sheet with id => {}", expenseSheet.getId());
        LOGGER.info("Redirecting to => {}", "/expenseSheet/" + expenseSheet.getId());

        System.out.println("expenseSheet = " + expenseSheet.getId());

        return new ModelAndView("redirect:/expense-sheet/" + expenseSheet.getId());
    }

    @RequestMapping(value = "/expense-sheet/{expenseSheetId}", method = RequestMethod.GET)
    public ModelAndView getExpenseSheet(@PathVariable int expenseSheetId) {
        ExpenseSheet expenseSheet = expenseSheetService.findExpenseSheet(expenseSheetId);

        LOGGER.info("Number of users => {}", expenseSheet.getUsers().size());
        LOGGER.info("Number of expenses => ", expenseSheet.getExpenses().size());

        Map<String, ExpenseSheet> model = newHashMap();
        model.put("expenseSheet", expenseSheet);

        return new ModelAndView("expense_sheet", model);
    }
}
