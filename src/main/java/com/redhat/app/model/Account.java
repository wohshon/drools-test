package com.redhat.app.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class Account {

    private Integer balance;
    private String status;
    private String accountId;    
    
    public Account(Integer balance, String accountId) {
        super();
        this.balance = balance;
        this.accountId = accountId;
    }
    public void withdraw(int money) {
        balance -= money;
    }

}
