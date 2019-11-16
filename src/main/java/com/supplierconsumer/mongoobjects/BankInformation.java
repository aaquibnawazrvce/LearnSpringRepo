package com.supplierconsumer.mongoobjects;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="bankinformation")
public class BankInformation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getIpin() {
		return ipin;
	}

	public void setIpin(String ipin) {
		this.ipin = ipin;
	}

	@Id
	private String accountno;
	
	public String getAccountno() {
		return accountno;
	}

	public void setAccountno(String accountno) {
		this.accountno = accountno;
	}

	private double balance;
	
	private String ipin;
	
	
}
