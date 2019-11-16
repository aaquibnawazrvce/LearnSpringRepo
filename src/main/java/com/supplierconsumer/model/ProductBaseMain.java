package com.supplierconsumer.model;

import java.io.Serializable;

public class ProductBaseMain implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private double price;
	
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getProductImgLoc() {
		return productImgLoc;
	}

	public void setProductImgLoc(String productImgLoc) {
		this.productImgLoc = productImgLoc;
	}

	private String productDescription;
	
	private String productImgLoc;
	
	

}
