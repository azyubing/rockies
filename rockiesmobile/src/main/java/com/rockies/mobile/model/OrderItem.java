package com.rockies.mobile.model;

public class OrderItem {
	private int id;//项目唯一标识
	private String tid;
	private String tname;
	private int num;
	private int extrabedNum;//加床数量，对于别墅有效
	private double price;

	public OrderItem() {

	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getExtrabedNum() {
		return extrabedNum;
	}

	public void setExtrabedNum(int extrabedNum) {
		this.extrabedNum = extrabedNum;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
