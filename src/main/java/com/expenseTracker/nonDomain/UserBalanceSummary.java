package com.expenseTracker.nonDomain;

public class UserBalanceSummary implements Comparable<UserBalanceSummary> {
    public Integer userId;
    public String userName;
    public Integer balance;

    public UserBalanceSummary(Integer userId, String userName, Integer balance) {
        this.userId = userId;
        this.userName = userName;
        this.balance = balance;
    }

    @Override
    public int compareTo(UserBalanceSummary userBalanceSummary) {
        return this.balance - userBalanceSummary.balance;
    }
}
