package com.expenseTracker.controller;

import com.expenseTracker.domain.ExpenseSheet;
import com.expenseTracker.nonDomain.Settlement;
import com.expenseTracker.service.DebtSettlementService;
import com.expenseTracker.service.ExpenseSheetService;
import com.expenseTracker.viewmodel.SettlementViewModel;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

@Controller
public class ExpenseSheetController {
    private final Logger LOGGER = LoggerFactory.getLogger(ExpenseSheetController.class);

    private ExpenseSheetService expenseSheetService;
    private DebtSettlementService debtSettlementService;

    @Autowired
    public ExpenseSheetController(ExpenseSheetService expenseSheetService, DebtSettlementService debtSettlementService) {
        this.expenseSheetService = expenseSheetService;
        this.debtSettlementService = debtSettlementService;
    }

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

        return new ModelAndView("redirect:/expense-sheet/" + expenseSheet.getId());
    }

    @RequestMapping(value = "/expense-sheet/{expenseSheetId}", method = RequestMethod.GET)
    public ModelAndView getExpenseSheet(@PathVariable int expenseSheetId) {
        ExpenseSheet expenseSheet = expenseSheetService.getExpenseSheet(expenseSheetId);

        Map<String, ExpenseSheet> model = newHashMap();
        model.put("expenseSheet", expenseSheet);

        return new ModelAndView("expense_sheet", model);
    }

    @RequestMapping(value = "/expense-sheet/{expenseSheetId}/settlements", method = RequestMethod.GET)
    public ModelAndView getSettlementList(@PathVariable int expenseSheetId) {
        ExpenseSheet expenseSheet = expenseSheetService.getExpenseSheet(expenseSheetId);

        List<Settlement> settlements = debtSettlementService.computeSettlements(expenseSheet);

        List<SettlementViewModel> settlementViewModelList = Lists.transform(settlements, settlement -> new SettlementViewModel(settlement.debtorName, settlement.creditorName, settlement.amountSettled));

        Map<String, List<SettlementViewModel>> model = newHashMap();
        model.put("settlements", settlementViewModelList);

        return new ModelAndView("settlements", model);
    }
}
