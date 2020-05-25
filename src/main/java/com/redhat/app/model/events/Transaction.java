package com.redhat.app.model.events;

import java.util.Date;

import com.redhat.app.model.Account;
import com.redhat.app.model.Customer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Transaction {
	
	private Customer<Account> customer;
	private Date timestamp;
	private TX_TYPE transactionType;
	private Integer amount;
	private TX_LOCATION location;
	private String id;
	
	public enum TX_TYPE {
		DEPOSIT,
		WITHDRAWAL
	}

	public enum TX_LOCATION {
		NORTH,
		SOUTH,
		EAST,
		WEST
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("id: ").append(this.getId()).append(" location: ").append(this.getLocation()).append(" customer:").append(customer.getAccount().getAccountId())
		.append(" amount:").append(this.getAmount());
		return sb.toString();
	}
}
