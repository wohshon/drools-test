package com.redhat.app.model;

public class SpecialAccount extends Account {

	private double monthlyInterest;
	
	public SpecialAccount(int balance, double monthlyInterest, String id) {
		super(balance,id);
		this.setMonthlyInterest(monthlyInterest);
	}
	
	public double getMonthlyInterest() {
		return monthlyInterest;
	}

	public void setMonthlyInterest(double monthlyInterest) {
		this.monthlyInterest = monthlyInterest;
	}
	
	
}
