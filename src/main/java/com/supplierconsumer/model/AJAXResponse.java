package com.supplierconsumer.model;

import java.util.List;

/**
 * 
 *
 */
public class AJAXResponse {
	private boolean status;
	private List<Message> errMessages;
	private Object model;
	private int totalSize;
	private String tokenName;
	private String tokenValue;
	private Object[] ssurtTokenValues;
	private String message;
	
	private int total;

	/**
	 * @return Object
	 */
	public Object[] getSsurtTokenValues() {
		return ssurtTokenValues;
	}

	/**
	 * @param ssurtTokenValues
	 */
	public void setSsurtTokenValues(Object[] ssurtTokenValues) {
		this.ssurtTokenValues = ssurtTokenValues;
		if (null != ssurtTokenValues[1]) {
			setTokenName(ssurtTokenValues[1].toString());
		}
		if (null != ssurtTokenValues[2]) {
			setTokenValue(ssurtTokenValues[1].toString());
		}
	}

	/**
	 * @return Token name
	 */
	public String getTokenName() {
		return tokenName;
	}

	/**
	 * @param tokenName
	 */
	public void setTokenName(String tokenName) {
		this.tokenName = tokenName;
	}

	/**
	 * @return token value
	 */
	public String getTokenValue() {
		return tokenValue;
	}

	/**
	 * @param tokenValue
	 */
	public void setTokenValue(String tokenValue) {
		this.tokenValue = tokenValue;
	}

	/**
	 * @return sucess message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return total records if list
	 */
	public int getTotalSize() {
		return totalSize;
	}

	/**
	 * @param i
	 */
	public void setTotalSize(int i) {
		this.totalSize = i;
	}

	/**
	 * 
	 */
	public AJAXResponse() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return true if sucess otherwise failure
	 */
	public boolean isStatus() {
		return status;
	}

	/**
	 * @param status
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}

	/**
	 * @return Object
	 */
	public Object getModel() {
		return model;
	}

	/**
	 * @param model
	 */
	public void setModel(Object model) {
		this.model = model;
	}

	public List<Message> getErrMessages() {
		return errMessages;
	}

	public void setErrMessages(List<Message> errMessages) {
		this.errMessages = errMessages;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
}
