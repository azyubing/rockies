package com.rockies.model;

public class CustomerVO extends Customer {
	private String orderNo;
	private int type;
	private int bookCusId;
	@Override
	public String toString() {
		return "CustomerVO [orderNo=" + orderNo + ", type=" + type
				+ ", bookCusId=" + bookCusId + "]";
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getBookCusId() {
		return bookCusId;
	}
	public void setBookCusId(int bookCusId) {
		this.bookCusId = bookCusId;
	}
}
