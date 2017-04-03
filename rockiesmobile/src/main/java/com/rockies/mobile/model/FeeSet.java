package com.rockies.mobile.model;

import java.math.BigDecimal;

public class FeeSet {
	private String name;
	private BigDecimal feeTotal;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getFeeTotal() {
		return feeTotal;
	}

	public void setFeeTotal(BigDecimal bigDecimal) {
		this.feeTotal = bigDecimal;
	}
}
