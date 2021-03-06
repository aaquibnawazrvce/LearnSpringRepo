package com.supplierconsumer.uiobjects;

import java.io.Serializable;

public class SupplierRevenueSummary implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public double getTotalRevenue() {
		return totalRevenue;
	}

	public void setTotalRevenue(double totalRevenue) {
		this.totalRevenue = totalRevenue;
	}

	public double getAvgRevenue() {
		return avgRevenue;
	}

	public void setAvgRevenue(double avgRevenue) {
		this.avgRevenue = avgRevenue;
	}

	private String supplierName;
	
	private double totalRevenue;
	
	private double avgRevenue;

}
