package com.expenseTracker.service;

import com.expenseTracker.domain.Expense;
import com.expenseTracker.domain.ExpenseSheet;
import com.expenseTracker.domain.User;
import com.expenseTracker.nonDomain.Settlement;
import com.expenseTracker.nonDomain.UserBalanceSummary;
import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;

@Component
public class DebtSettlementServiceImpl implements DebtSettlementService {

    @Autowired
    private DebtSettlementHelper debtSettlementHelper;

    @Override
    public List<Settlement> computeSettlements(ExpenseSheet expenseSheet) {
        List<UserBalanceSummary> userBalanceSummaries = createUserBalanceSummaries(expenseSheet.getExpenses(), expenseSheet.getUsers());

        return debtSettlementHelper.computeSettlementForDebts(userBalanceSummaries);
    }

    private List<UserBalanceSummary> createUserBalanceSummaries(List<Expense> expenses, List<User> users) {
        Map<Integer, UserBalanceSummary> userBalanceSummaryMap = getUserBalanceSummaryMap(users);

        for(Expense expense : expenses) {
            UserBalanceSummary creditor = userBalanceSummaryMap.get(expense.getPayer().getId());
            creditor.balance += expense.getAmountPaid();

            for(User beneficiary : expense.getBeneficiaries()) {
                UserBalanceSummary debtor = userBalanceSummaryMap.get(beneficiary.getId());
                debtor.balance -= expense.getAmountPaid() / expense.getBeneficiaries().size();
            }
        }

       return newArrayList(Collections2.transform(userBalanceSummaryMap.entrySet(), new Function<Map.Entry<Integer, UserBalanceSummary>, UserBalanceSummary>() {
           @Override
           public UserBalanceSummary apply(Map.Entry<Integer, UserBalanceSummary> entry) {
               return entry.getValue();
           }
       }));
    }

    private Map<Integer, UserBalanceSummary> getUserBalanceSummaryMap(List<User> users) {
        List<UserBalanceSummary> userBalanceSummaries = getUserBalanceSummaries(users);

        return Maps.uniqueIndex(userBalanceSummaries, new Function<UserBalanceSummary, Integer>() {
            @Override
            public Integer apply(UserBalanceSummary userBalanceSummary) {
                return userBalanceSummary.userId;
            }
        });
    }

    private List<UserBalanceSummary> getUserBalanceSummaries(List<User> users) {
        return Lists.transform(users, new Function<User, UserBalanceSummary>() {
            @Override
            public UserBalanceSummary apply(User user) {
                UserBalanceSummary userBalanceSummary = new UserBalanceSummary(user.getId(), user.getName(), 0);

                return userBalanceSummary;
            }
        });
    }
}
