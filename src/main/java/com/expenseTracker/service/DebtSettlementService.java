package com.expenseTracker.service;

import com.expenseTracker.domain.ExpenseSheet;
import com.expenseTracker.nonDomain.Settlement;

import java.util.List;

public interface DebtSettlementService {
    List<Settlement> computeSettlements(ExpenseSheet expenseSheet);
}
