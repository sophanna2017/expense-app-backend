package com.gigster.expense.service;

import com.gigster.expense.domain.AdminUser;
import com.gigster.expense.domain.User;
import com.gigster.expense.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createRegularUser(String username, String password) {
        return userRepository.save(new User(username, passwordEncoder.encode(password), null));
    }

    public User createAdminUser(String username, String password) {
        return userRepository.save(new AdminUser(username, passwordEncoder.encode(password)));
    }

    public User getUserById(int id) {
        return userRepository.findOne(id);
    }

    public Iterable<User> getAll() {
        return userRepository.findAll();
    }

    public User getUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        user.setPassword("");
        return user;
    }
}

