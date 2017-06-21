package com.gigster.expense.web;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ExpenseDto {

    @NotNull
    private Long transactionDate;

    @Min(0)
    private Double amount;

    @Size(max = 255)
    private String description;

    private int userId;

    private int expendId;

    public Long getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Long transactionDate) {
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getExpendId() {
        return expendId;
    }

    public void setExpendId(int expendId) {
        this.expendId = expendId;
    }
}
