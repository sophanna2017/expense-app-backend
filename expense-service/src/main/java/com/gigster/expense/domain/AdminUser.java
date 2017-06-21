package com.gigster.expense.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("admin")
public class AdminUser extends User {

    protected AdminUser() {

    }

    public AdminUser(String username, String password) {
        super(username, password, null);
    }
}
