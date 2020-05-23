package com.redhat.app.model.events;

import java.util.Date;

import com.redhat.app.model.Account;
import com.redhat.app.model.Customer;

public class Transaction {
	
	private Customer<Account> customer;
	private Date timestamp;
	private TX_TYPE transactionType;
	private Integer amount;
	public enum TX_TYPE {
		DEPOSIT,
		WITHDRAWAL
	}
	public Customer<Account> getCustomer() {
		return customer;
	}
	public void setCustomer(Customer<Account> customer) {
		this.customer = customer;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public TX_TYPE getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(TX_TYPE transactionType) {
		this.transactionType = transactionType;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
}
