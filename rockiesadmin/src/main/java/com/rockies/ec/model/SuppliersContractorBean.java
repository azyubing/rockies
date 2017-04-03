package com.rockies.ec.model;

/**
 * 供应商联系人
 * @author hyh
 *
 */
public class SuppliersContractorBean extends BaseModel {
	private int cid ;//联系人编号
	private int sid ;//供应商主键
	private String c_name;//联系人名
	private String c_tel;//
	private String c_mobile_phone;//
	private String c_email;//
	private String c_fax;//
	private String c_address;//地址
	private int is_primary;//是否主要联系人 0false 1true
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getC_name() {
		return c_name;
	}
	public void setC_name(String c_name) {
		this.c_name = c_name;
	}
	public String getC_tel() {
		return c_tel;
	}
	public void setC_tel(String c_tel) {
		this.c_tel = c_tel;
	}
	public String getC_mobile_phone() {
		return c_mobile_phone;
	}
	public void setC_mobile_phone(String c_mobile_phone) {
		this.c_mobile_phone = c_mobile_phone;
	}
	public String getC_email() {
		return c_email;
	}
	public void setC_email(String c_email) {
		this.c_email = c_email;
	}
	public String getC_fax() {
		return c_fax;
	}
	public void setC_fax(String c_fax) {
		this.c_fax = c_fax;
	}
	public String getC_address() {
		return c_address;
	}
	public void setC_address(String c_address) {
		this.c_address = c_address;
	}
	public int getIs_primary() {
		return is_primary;
	}
	public void setIs_primary(int is_primary) {
		this.is_primary = is_primary;
	}
	@Override
	public String toString() {
		return "SuppliersContractorBean [cid=" + cid + ", sid=" + sid
				+ ", c_name=" + c_name + ", c_tel=" + c_tel
				+ ", c_mobile_phone=" + c_mobile_phone + ", c_email=" + c_email
				+ ", c_fax=" + c_fax + ", c_address=" + c_address
				+ ", is_primary=" + is_primary + "]";
	}
	
}
