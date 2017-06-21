package com.gigster.expense.repository;

import com.gigster.expense.domain.Expense;
import com.gigster.expense.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface ExpenseRepository extends CrudRepository<Expense, Integer> {

    List<Expense> findByUser(User user);

    List<Expense> findByUserAndTransactionDate(User user, Date requestedDate);
}
