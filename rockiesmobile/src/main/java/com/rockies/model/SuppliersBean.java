package com.rockies.model;

/**
 * 供应商
 *
 */
public class SuppliersBean extends BaseModel {
	private int sid;//供应商主键
	private String s_name;//供应商名称
	private int s_status;//0暂停 1使用中 2撤销
	private int s_country;//供应商所属国家编号
	private String s_remark;//备注
	private String currency_type;//结算币种
	private int pay_type;//付款方式
	private String pay_account;//付款账号
	private int del_flg;//是否删除
	
	@Override
	public String toString() {
		return "SuppliersBean [sid=" + sid + ", s_name=" + s_name
				+ ", s_status=" + s_status + ", s_country=" + s_country
				+ ", s_remark=" + s_remark + ", currency_type=" + currency_type
				+ ", pay_type=" + pay_type + ", pay_account=" + pay_account
				+ ", del_flg=" + del_flg + "]";
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getS_name() {
		return s_name;
	}
	public void setS_name(String s_name) {
		this.s_name = s_name;
	}
	public int getS_status() {
		return s_status;
	}
	public void setS_status(int s_status) {
		this.s_status = s_status;
	}
	public int getS_country() {
		return s_country;
	}
	public void setS_country(int s_country) {
		this.s_country = s_country;
	}
	public String getS_remark() {
		return s_remark;
	}
	public void setS_remark(String s_remark) {
		this.s_remark = s_remark;
	}
	public String getCurrency_type() {
		return currency_type;
	}
	public void setCurrency_type(String currency_type) {
		this.currency_type = currency_type;
	}
	public int getPay_type() {
		return pay_type;
	}
	public void setPay_type(int pay_type) {
		this.pay_type = pay_type;
	}
	public String getPay_account() {
		return pay_account;
	}
	public void setPay_account(String pay_account) {
		this.pay_account = pay_account;
	}
	public int getDel_flg() {
		return del_flg;
	}
	public void setDel_flg(int del_flg) {
		this.del_flg = del_flg;
	}
	
	
	
}
