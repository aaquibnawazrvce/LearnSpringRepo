package com.supplierconsumer.uiobjects;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

public class OrdersPerSupplier implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String supplierName;

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public double getTransactioncost() {
		return transactioncost;
	}

	public void setTransactioncost(double transactioncost) {
		this.transactioncost = transactioncost;
	}

	private double transactioncost;

}
