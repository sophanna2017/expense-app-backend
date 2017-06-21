package com.gigster.expense.controller;

import com.gigster.expense.domain.Expense;
import com.gigster.expense.service.ExpenseService;
import com.gigster.expense.web.ExpenseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/expense")
public class ExpenseController {

    @Autowired
    ExpenseService expenseService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity createExpense(@RequestBody ExpenseDto expenseDto) {
        if (expenseService.createExpense(expenseDto) != null) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/getExpenseByWeek/{userId}/{startDateFilter}", method = RequestMethod.GET)
    public List<Expense> getAllExpensesByUserId(@PathVariable int userId,
                                                @PathVariable Long startDateFilter) {
        Calendar startDate = Calendar.getInstance();
        startDate.setTimeInMillis(startDateFilter);
        return expenseService.getExpenseByUserIdAndWeek(userId, startDate.getTime());
    }
}
