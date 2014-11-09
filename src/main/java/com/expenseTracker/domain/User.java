package com.expenseTracker.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user")
public class User implements Serializable {
    private Integer id;
    private String name;
    private Double balance;
    private Integer expenseSheetId;

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

    @Column(name = "expense_sheet_id")
    public Integer getExpenseSheetId() {
        return expenseSheetId;
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

    public void setExpenseSheetId(Integer expenseSheetId) {
        this.expenseSheetId = expenseSheetId;
    }
}
