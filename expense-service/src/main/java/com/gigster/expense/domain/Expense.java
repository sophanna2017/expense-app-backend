package com.gigster.expense.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Expense implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private Date transactionDate;

    @Column
    private Double amount;

    @Column
    private String description;

    @ManyToOne
    private User user;

    public Expense(Date transactionDate, Double amount, String description, User user) {
        this.transactionDate = transactionDate;
        this.amount = amount;
        this.description = description;
        this.user = user;
    }

    protected Expense() {

    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
