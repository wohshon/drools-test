package com.redhat.app.model;

public class SpecialAccount extends Account {

	private double monthlyInterest;
	
	public SpecialAccount(int balance, double monthlyInterest) {
		super(balance);
		this.setMonthlyInterest(monthlyInterest);
	}
	
	public double getMonthlyInterest() {
		return monthlyInterest;
	}

	public void setMonthlyInterest(double monthlyInterest) {
		this.monthlyInterest = monthlyInterest;
	}
	
	
}
