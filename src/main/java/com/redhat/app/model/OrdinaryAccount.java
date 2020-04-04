package com.redhat.app.model;

public class OrdinaryAccount extends Account {
	private double yearlyInterest;

	public OrdinaryAccount(int balance, double yearlyInterest) {
		super(balance);
		this.setYearlyInterest(yearlyInterest);
	}
	public double getYearlyInterest() {
		return this.yearlyInterest;
	}

	public void setYearlyInterest(double yearlyInterest) {
		this.yearlyInterest = yearlyInterest;
	}
	
	
}
