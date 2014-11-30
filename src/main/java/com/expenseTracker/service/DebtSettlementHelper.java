package com.expenseTracker.service;

import com.expenseTracker.nonDomain.Settlement;
import com.expenseTracker.nonDomain.UserBalanceSummary;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Math.abs;

@Component
public class DebtSettlementHelper {
    public List<Settlement> computeSettlementForDebts(List<UserBalanceSummary> userBalanceSummaryList) {
        List<Settlement> settlements = new ArrayList<Settlement>();
        Collections.sort(userBalanceSummaryList);

        for (UserBalanceSummary debtor : userBalanceSummaryList) {
            int idx = userBalanceSummaryList.size() - 1;
            while (debtor.balance < 0) {
                UserBalanceSummary creditor = userBalanceSummaryList.get(idx);
                if (creditor.balance > 0) {
                    Settlement settlement = new Settlement(creditor.userId, creditor.userName, debtor.userId, debtor.userName, 0);

                    if (abs(debtor.balance) > creditor.balance) {
                        settlement.amountSettled = creditor.balance;
                        debtor.balance += creditor.balance;
                        creditor.balance = 0;
                    } else {
                        settlement.amountSettled = abs(debtor.balance);
                        creditor.balance -= abs(debtor.balance);
                        debtor.balance = 0;
                    }

                    settlements.add(settlement);
                }

                --idx;
            }
        }

        return settlements;
    }
}
