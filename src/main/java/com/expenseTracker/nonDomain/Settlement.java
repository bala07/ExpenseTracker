package com.expenseTracker.nonDomain;

public class Settlement {
    public Integer creditorId;
    public String creditorName;
    public Integer debtorId;
    public String debtorName;
    public Integer amountSettled;

    public Settlement(Integer creditorId, String creditorName, Integer debtorId, String debtorName, Integer amountSettled) {
        this.creditorId = creditorId;
        this.creditorName = creditorName;
        this.debtorId = debtorId;
        this.debtorName = debtorName;
        this.amountSettled = amountSettled;
    }
}
