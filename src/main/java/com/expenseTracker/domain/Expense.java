package com.expenseTracker.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "expense")
public class Expense implements Serializable {

    private Integer id;
    private ExpenseSheet expenseSheet;
    private User payer;
    private Integer amountPaid;
    private List<User> beneficiaries;

    @Id
    @GeneratedValue
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "expense_sheet_id", referencedColumnName = "id")
    public ExpenseSheet getExpenseSheet() {
        return expenseSheet;
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "payer_id", referencedColumnName = "id")
    public User getPayer() {
        return payer;
    }

    @Column(name = "amount_paid")
    public Integer getAmountPaid() {
        return amountPaid;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "expense_user",
            joinColumns = {@JoinColumn(name = "expense_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")})
    public List<User> getBeneficiaries() {
        return beneficiaries;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setExpenseSheet(ExpenseSheet expenseSheet) {
        this.expenseSheet = expenseSheet;
    }

    public void setBeneficiaries(List<User> paidForUsers) {
        this.beneficiaries = paidForUsers;
    }

    public void setPayer(User payer) {
        this.payer = payer;
    }

    public void setAmountPaid(Integer amountPaid) {
        this.amountPaid = amountPaid;
    }
}
