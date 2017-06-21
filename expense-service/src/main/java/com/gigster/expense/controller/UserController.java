package com.gigster.expense.controller;

import com.gigster.expense.domain.User;
import com.gigster.expense.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "getByUsername/{username}", method = RequestMethod.GET)
    public User getUserInfo(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }
}
