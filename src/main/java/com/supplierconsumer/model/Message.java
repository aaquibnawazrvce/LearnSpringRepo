package com.supplierconsumer.model;

import java.io.Serializable;

/**
 * @author Bushra
 *
 */
public class Message implements Serializable {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	private String errMessage;
	public String getErrMessage() {
		return errMessage;
	}
	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}

	

}
