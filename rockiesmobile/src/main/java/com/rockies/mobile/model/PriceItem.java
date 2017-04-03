package com.rockies.mobile.model;

public class PriceItem {
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public PriceItem(String status) {
		this.setStatus(status);
	}

}
