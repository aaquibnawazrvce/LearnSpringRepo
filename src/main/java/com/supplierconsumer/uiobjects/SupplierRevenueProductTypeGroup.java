package com.supplierconsumer.uiobjects;

import java.io.Serializable;

public class SupplierRevenueProductTypeGroup extends SupplierRevenueSummary implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int producttype;

	public int getProducttype() {
		return producttype;
	}

	public void setProducttype(int producttype) {
		this.producttype = producttype;
	}


}
