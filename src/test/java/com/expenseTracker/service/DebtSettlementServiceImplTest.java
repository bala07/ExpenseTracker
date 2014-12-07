package com.expenseTracker.service;

import com.expenseTracker.domain.Expense;
import com.expenseTracker.domain.ExpenseSheet;
import com.expenseTracker.domain.User;
import com.expenseTracker.nonDomain.UserBalanceSummary;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.testng.Assert.assertTrue;

public class DebtSettlementServiceImplTest {
    private DebtSettlementService debtSettlementService;
    @Mock
    private DebtSettlementHelper debtSettlementHelper;
    @Captor
    ArgumentCaptor<ArrayList<UserBalanceSummary>> summaryCaptor;


    @BeforeMethod
    public void setup() {
        initMocks(this);
        debtSettlementService = new DebtSettlementServiceImpl(debtSettlementHelper);
    }

    @Test
    public void shouldComputeCorrectUserBalanceSummaries1() {
        List<User> users = getUserList(4);
        Expense expense1 = newExpense(users.get(2), newArrayList(users.get(1)), 10);
        Expense expense2 = newExpense(users.get(3), newArrayList(users.get(1)), 10);
        Expense expense3 = newExpense(users.get(3), newArrayList(users.get(0)), 20);
        ExpenseSheet expenseSheet = newExpenseSheet(users, newArrayList(expense1, expense2, expense3));

        when(debtSettlementHelper.computeSettlementForDebts(summaryCaptor.capture())).thenReturn(null);

        debtSettlementService.computeSettlements(expenseSheet);

        List<UserBalanceSummary> summaries = summaryCaptor.getValue();
        assertTrue(summaries.stream().anyMatch(summary -> summary.userId == 1 && summary.balance == -20));
        assertTrue(summaries.stream().anyMatch(summary -> summary.userId == 2 && summary.balance == -20));
        assertTrue(summaries.stream().anyMatch(summary -> summary.userId == 3 && summary.balance == 10));
        assertTrue(summaries.stream().anyMatch(summary -> summary.userId == 4 && summary.balance == 30));
    }

    @Test
    public void shouldComputeCorrectUserBalanceSummaries2() {
        List<User> users = getUserList(2);
        Expense expense1 = newExpense(users.get(0), newArrayList(users.get(1), users.get(0)), 100);
        ExpenseSheet expenseSheet = newExpenseSheet(users, newArrayList(expense1));

        when(debtSettlementHelper.computeSettlementForDebts(summaryCaptor.capture())).thenReturn(null);

        debtSettlementService.computeSettlements(expenseSheet);

        List<UserBalanceSummary> summaries = summaryCaptor.getValue();
        assertTrue(summaries.stream().anyMatch(summary -> summary.userId == 1 && summary.balance == 50));
        assertTrue(summaries.stream().anyMatch(summary -> summary.userId == 2 && summary.balance == -50));
    }

    private List<User> getUserList(int count) {
        List<User> users = newArrayList();
        for(int idx = 1; idx <= count; ++idx) {
            User user = new User();
            user.setId(idx);
            users.add(user);
        }

        return users;
    }

    private Expense newExpense(User payer, List<User> beneficiaries, Integer amountPaid) {
        Expense expense = new Expense();
        expense.setPayer(payer);
        expense.setBeneficiaries(beneficiaries);
        expense.setAmountPaid(amountPaid);

        return expense;
    }

    private ExpenseSheet newExpenseSheet(List<User> users, List<Expense> expenses) {
        ExpenseSheet expenseSheet = new ExpenseSheet();
        expenseSheet.setUsers(users);
        expenseSheet.setExpenses(expenses);

        return expenseSheet;
    }
}