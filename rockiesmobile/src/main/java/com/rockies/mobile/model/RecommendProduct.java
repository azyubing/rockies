package com.rockies.mobile.model;

import java.math.BigDecimal;

public class RecommendProduct {
	private BigDecimal price;
	private int confirmTimes;
	private String name;
	private String description;
	private String pname;
	private String images;
	private String cover;

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getConfirmTimes() {
		return confirmTimes;
	}

	public void setConfirmTimes(int confirmTimes) {
		this.confirmTimes = confirmTimes;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}
}
