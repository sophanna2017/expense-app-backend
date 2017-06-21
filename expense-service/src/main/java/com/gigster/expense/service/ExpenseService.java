package com.gigster.expense.service;

import com.gigster.expense.domain.Expense;
import com.gigster.expense.domain.User;
import com.gigster.expense.repository.ExpenseRepository;
import com.gigster.expense.repository.UserRepository;
import com.gigster.expense.web.ExpenseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserRepository userRepository;

    public Expense createExpense(ExpenseDto expenseDto) {
        Calendar expenseDate = Calendar.getInstance();
        expenseDate.setTimeInMillis(expenseDto.getTransactionDate());
        Expense expense = null;
        if (expenseDto.getExpendId() != 0) {
            expense = expenseRepository.findOne(expenseDto.getExpendId());
            expense.setAmount(expenseDto.getAmount());
            expense.setDescription(expenseDto.getDescription());
            expense.setTransactionDate(expenseDate.getTime());
        } else {
            User user = userRepository.findOne(expenseDto.getUserId());
            expense = new Expense(expenseDate.getTime(), expenseDto.getAmount(), expenseDto.getDescription(), user);
        }
        return expenseRepository.save(expense);
    }

    public List<Expense> getAllExpensesByUserId(int userId) {
        User user = userRepository.findOne(userId);
        return expenseRepository.findByUser(user);
    }

    public List<Expense> getExpenseByUserIdAndWeek(int userId, Date startDate) {
        User user = userRepository.findOne(userId);
        List<Expense> expenseList = new ArrayList<>();
        for (int i=0; i<7; i++) {
            Date requestedDate = new Date(startDate.getTime() + TimeUnit.DAYS.toMillis(i));
            List<Expense> result = expenseRepository.findByUserAndTransactionDate(user, requestedDate);
            if (result != null && result.size() > 0) {
                expenseList.addAll(result);
            }
        }

        return expenseList;
    }

}
