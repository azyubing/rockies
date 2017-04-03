package com.rockies.ec.model;

/**
 * 产品订单详情
 * @author hyh
 *
 */
public class ProductOrderDetail extends BaseModel {
	private String orderNo;//行程单编号
	private String runingNo;//流水号
	private String priceItem;//价格项
	private String pid;//产品ID
	private int ordercnt;//预定数
	private double unitPrice;//单价
	private double unitPrepay;//单价预付额
	private int peoplecnt;//人数
	private double amount;//总计价格
	private double prepayAmount;//预付总计额
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getRuningNo() {
		return runingNo;
	}
	public void setRuningNo(String runingNo) {
		this.runingNo = runingNo;
	}
	public String getPriceItem() {
		return priceItem;
	}
	public void setPriceItem(String priceItem) {
		this.priceItem = priceItem;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public int getOrdercnt() {
		return ordercnt;
	}
	public void setOrdercnt(int ordercnt) {
		this.ordercnt = ordercnt;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public double getUnitPrepay() {
		return unitPrepay;
	}
	public void setUnitPrepay(double unitPrepay) {
		this.unitPrepay = unitPrepay;
	}
	public int getPeoplecnt() {
		return peoplecnt;
	}
	public void setPeoplecnt(int peoplecnt) {
		this.peoplecnt = peoplecnt;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getPrepayAmount() {
		return prepayAmount;
	}
	public void setPrepayAmount(double prepayAmount) {
		this.prepayAmount = prepayAmount;
	}
	@Override
	public String toString() {
		return "ProductOrderDetail [orderNo=" + orderNo + ", runingNo="
				+ runingNo + ", priceItem=" + priceItem + ", pid=" + pid
				+ ", ordercnt=" + ordercnt + ", unitPrice=" + unitPrice
				+ ", unitPrepay=" + unitPrepay + ", peoplecnt=" + peoplecnt
				+ ", amount=" + amount + ", prepayAmount=" + prepayAmount + "]";
	}
	
	
}
