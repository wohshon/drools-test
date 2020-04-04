package com.redhat.app.model;

public abstract class Account {

    private Integer balance;
    private String status;    
    
    public Account() {}
    public Integer getBalance() {
        return balance;
    }
    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public void setStatus(String status) {
        this.status=status;
    }

    public String getStatus() {
	return this.status;
    }
    public Account(Integer balance) {
        super();
        this.balance = balance;
    }
    public void withdraw(int money) {
        balance -= money;
    }

}
