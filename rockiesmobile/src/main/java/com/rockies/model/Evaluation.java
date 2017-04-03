package com.rockies.model;

/**
 * 评论
 *
 */
public class Evaluation extends BaseModel {
	private int id;
	private String customerName;//客户用户名
	private String productId;//产品编号
	private String accountnumber;//帐号
	private int core;//分
	private String content;//内容
	private int userId;// 用户id
	private String photo;//头像
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getAccountnumber() {
		return accountnumber;
	}
	public void setAccountnumber(String accountnumber) {
		this.accountnumber = accountnumber;
	}
	public int getCore() {
		return core;
	}
	public void setCore(int core) {
		this.core = core;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int i) {
		this.userId = i;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	@Override
	public String toString() {
		return "Evaluation [id=" + id + ", customerName=" + customerName
				+ ", productId=" + productId + ", accountnumber="
				+ accountnumber + ", core=" + core + ", content=" + content
				+ ", userId=" + userId + ", photo=" + photo + "]";
	}
	
}
