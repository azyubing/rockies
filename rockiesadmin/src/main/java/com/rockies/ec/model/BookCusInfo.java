package com.rockies.ec.model;

/**
 * 预定人信息
 *
 */
public class BookCusInfo extends BaseModel {
	private int id;//行程单编号
	private String orderNo;//行程单编号
	private String cusNo;//客户编号 : 和客户联系人信息表主键挂钩
	private String imgPath;//凭证图片
	private int type;//是否是主要联系人2：是，1：次要联系人，0：都不是
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public String getCusNo() {
		return cusNo;
	}
	public void setCusNo(String cusNo) {
		this.cusNo = cusNo;
	}
	@Override
	public String toString() {
		return "BookCusInfo [id=" + id + ", orderNo=" + orderNo + ", cusNo="
				+ cusNo + ", imgPath=" + imgPath + ", type=" + type + "]";
	}
	
	   
}
