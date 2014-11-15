package com.expenseTracker.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "expense")
public class Expense implements Serializable {

    private Integer id;
    private Integer expenseSheetId;
    private User payer;
    private List<User> beneficiaries;

    @Id
    @GeneratedValue
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    @Column(name = "expense_sheet_id")
    public Integer getExpenseSheetId() {
        return expenseSheetId;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "expense_user",
            joinColumns = {@JoinColumn(name = "expense_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")})
    public List<User> getBeneficiaries() {
        return beneficiaries;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(table = "user", name = "id", referencedColumnName = "payer_id", unique = true)
    public User getPayer() {
        return payer;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setExpenseSheetId(Integer expenseSheetId) {
        this.expenseSheetId = expenseSheetId;
    }

    public void setBeneficiaries(List<User> paidForUsers) {
        this.beneficiaries = paidForUsers;
    }

    public void setPayer(User payer) {
        this.payer = payer;
    }
}
