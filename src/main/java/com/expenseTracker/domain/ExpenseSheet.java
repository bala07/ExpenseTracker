package com.expenseTracker.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "expense_sheet")
public class ExpenseSheet {
    private Integer id;
    private List<Expense> expenses;
    private List<User> users;

    @Id
    @GeneratedValue
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    @OneToMany(mappedBy = "expenseSheet", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public List<Expense> getExpenses() {
        return expenses;
    }

    @OneToMany(mappedBy = "expenseSheet", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public List<User> getUsers() {
        return users;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
