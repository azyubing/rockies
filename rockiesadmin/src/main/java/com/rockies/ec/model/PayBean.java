package com.rockies.ec.model;

/**
 * 支付类型
 *
 */
public class PayBean extends BaseModel {
	private int pid;//支付类型id
	private String payType;//支付类型
	private String payName;//支付类型明晨
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getPayName() {
		return payName;
	}
	public void setPayName(String payName) {
		this.payName = payName;
	}
	@Override
	public String toString() {
		return "PayBean [pid=" + pid + ", payType=" + payType + ", payName="
				+ payName + "]";
	}
	
	
}
