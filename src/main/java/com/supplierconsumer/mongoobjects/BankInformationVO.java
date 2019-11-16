package com.supplierconsumer.mongoobjects;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


public class BankInformationVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	

	public String getIpin() {
		return ipin;
	}

	public void setIpin(String ipin) {
		this.ipin = ipin;
	}


	private String accountno;
	
	public String getAccountno() {
		return accountno;
	}

	public void setAccountno(String accountno) {
		this.accountno = accountno;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}


	private String balance;
	
	private String ipin;
	
	
}
