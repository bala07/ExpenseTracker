package com.expenseTracker.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "user")
public class User implements Serializable {
    private Integer id;
    private String name;
    private Double balance;
    private List<Expense> paidForExpenses;
    private ExpenseSheet expenseSheet;

    @Id
    @GeneratedValue
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    @Column(name = "balance")
    public Double getBalance() {
        return balance;
    }

    @OneToMany(mappedBy = "payer", fetch = FetchType.EAGER)
    public List<Expense> getPaidForExpenses() {
        return paidForExpenses;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "expense_sheet_id", referencedColumnName = "id")
    public ExpenseSheet getExpenseSheet() {
        return expenseSheet;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public void setExpenseSheet(ExpenseSheet expenseSheet) {
        this.expenseSheet = expenseSheet;
    }

    public void setPaidForExpenses(List<Expense> paidForExpenses) {
        this.paidForExpenses = paidForExpenses;
    }
}
