package com.gigster.expense;

import com.gigster.expense.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExpenseServiceApplication implements CommandLineRunner {

    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(ExpenseServiceApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        userService.createRegularUser("user1", "expense1");
        userService.createRegularUser("user2", "expense2");
        userService.createAdminUser("admin1", "expense1");
        userService.createAdminUser("admin2", "expense2");
    }
}
