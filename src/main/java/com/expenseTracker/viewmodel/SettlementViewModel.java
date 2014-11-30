package com.expenseTracker.viewmodel;

public class SettlementViewModel {
    public String debtorName;
    public String creditorName;
    public Integer amount;

    public SettlementViewModel(String debtorName, String creditorName, Integer amount) {
        this.debtorName = debtorName;
        this.creditorName = creditorName;
        this.amount = amount;
    }

    public String getDebtorName() {
        return debtorName;
    }

    public String getCreditorName() {
        return creditorName;
    }

    public Integer getAmount() {
        return amount;
    }
}
