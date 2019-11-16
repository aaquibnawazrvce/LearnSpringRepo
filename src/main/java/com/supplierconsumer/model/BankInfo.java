package com.supplierconsumer.model;

import java.io.Serializable;

import com.supplierconsumer.uiobjects.OrderInfo;

public class BankInfo extends OrderInfo implements Serializable{

	/**  
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDeliveryaddress() {
		return deliveryaddress;
	}

	public void setDeliveryaddress(String deliveryaddress) {
		this.deliveryaddress = deliveryaddress;
	}

	private String accountNo;
	
	private String password;
	
	
	private String deliveryaddress;
	

}
