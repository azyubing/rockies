package com.rockies.mobile.model;

import java.math.BigDecimal;

public class VillaPriceItem {

	public void setStatus(String status) {
		this.status = status;
	}

	public void setFeeBasic(BigDecimal bigDecimal) {
		this.feeBasic = bigDecimal;
	}

	public void setFeeTotal(double feeTotal) {
		this.feeTotal = feeTotal;
	}

	public void setFeeServe(double feeServe) {
		this.feeServe = feeServe;
	}

	public void setFeeTax(double feeTax) {
		this.feeTax = feeTax;
	}

	public void setDayMin(int dayMin) {
		this.dayMin = dayMin;
	}

	public void setBookMax(int bookMax) {
		this.bookMax = bookMax;
	}

	// {status:1,feeTotal:14143,feeBasic:13000,feeServe:620,feeTax:523,daysMin:3,bookMax:2}
	private String status;
	private BigDecimal feeBasic;
	private double feeTotal;
	private double feeServe;
	private double feeTax;

	public String getStatus() {
		return status;
	}

	public BigDecimal getFeeBasic() {
		return feeBasic;
	}

	public double getFeeTotal() {
		return feeTotal;
	}

	public double getFeeServe() {
		return feeServe;
	}

	public double getFeeTax() {
		return feeTax;
	}

	public int getDayMin() {
		return dayMin;
	}

	public int getBookMax() {
		return bookMax;
	}

	private int dayMin;
	private int bookMax;

}
