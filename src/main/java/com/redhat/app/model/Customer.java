package com.redhat.app.model;

public class Customer<T extends Account> {

	private String name;
	private T account;
	
	public Customer(String name) {
		this.setName(name);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public T getAccount() {
		return this.account;
	}
	public void setAccount(T account) {
		this.account = account;
	}
	
}
