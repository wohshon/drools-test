package com.redhat.app.model;

public class OrdinaryAccount extends Account {
	private double yearlyInterest;

	public OrdinaryAccount(int balance, double yearlyInterest, String id) {
		super(balance, id);
		this.setYearlyInterest(yearlyInterest);
	}
	public double getYearlyInterest() {
		return this.yearlyInterest;
	}

	public void setYearlyInterest(double yearlyInterest) {
		this.yearlyInterest = yearlyInterest;
	}
	
	
}
