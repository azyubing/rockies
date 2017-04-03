package com.rockies.ec.model;

import java.util.Date;


/**
 * 行程单
 * @author hyh
 *
 */
public class RouteOrder extends BaseModel {
	private String orderNo;//行程单编号
	private String cusNo;//客户编号
	private String orderNm;//订单名称
	private String status;//订单状态      1:待预付  2：已预付（待处理） 3：待付尾款  4：待出行  5：已出行 6:出行中 8：支付中 9：已退款
	private Date startdate;//行程开始日期
	private Date enddate;//行程结束日期
	private int adultCnt;//成人数
	private int childCnt;//儿童数
	private Double amount;//总价
	private Double prepay;;//预付款
	private String evaluateflg;//是否已评价
	private String delFlg;//有效FLG
	private Date billTime;//下单时间
	private Date prepayTime;//预付款支付时间
	private Date allpayTime;//尾款支付之间
	private String routeJson;//行程单json
	private String routeUsername;//联系人姓名
	private String routeTel;//联系电话
	private String routeEmail;//邮箱
	private Double oldAmount;//原来总价
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getCusNo() {
		return cusNo;
	}
	public void setCusNo(String cusNo) {
		this.cusNo = cusNo;
	}
	public String getOrderNm() {
		return orderNm;
	}
	public void setOrderNm(String orderNm) {
		this.orderNm = orderNm;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getStartdate() {
		return startdate;
	}
	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}
	public Date getEnddate() {
		return enddate;
	}
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
	public int getAdultCnt() {
		return adultCnt;
	}
	public void setAdultCnt(int adultCnt) {
		this.adultCnt = adultCnt;
	}
	public int getChildCnt() {
		return childCnt;
	}
	public void setChildCnt(int childCnt) {
		this.childCnt = childCnt;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Double getPrepay() {
		return prepay;
	}
	public void setPrepay(Double prepay) {
		this.prepay = prepay;
	}
	public String getEvaluateflg() {
		return evaluateflg;
	}
	public void setEvaluateflg(String evaluateflg) {
		this.evaluateflg = evaluateflg;
	}
	public String getDelFlg() {
		return delFlg;
	}
	public void setDelFlg(String delFlg) {
		this.delFlg = delFlg;
	}
	public Date getBillTime() {
		return billTime;
	}
	public void setBillTime(Date billTime) {
		this.billTime = billTime;
	}
	public Date getPrepayTime() {
		return prepayTime;
	}
	public void setPrepayTime(Date prepayTime) {
		this.prepayTime = prepayTime;
	}
	public Date getAllpayTime() {
		return allpayTime;
	}
	public void setAllpayTime(Date allpayTime) {
		this.allpayTime = allpayTime;
	}
	public String getRouteJson() {
		return routeJson;
	}
	public void setRouteJson(String routeJson) {
		this.routeJson = routeJson;
	}
	public String getRouteUsername() {
		return routeUsername;
	}
	public void setRouteUsername(String routeUsername) {
		this.routeUsername = routeUsername;
	}
	public String getRouteTel() {
		return routeTel;
	}
	public void setRouteTel(String routeTel) {
		this.routeTel = routeTel;
	}
	public String getRouteEmail() {
		return routeEmail;
	}
	public void setRouteEmail(String routeEmail) {
		this.routeEmail = routeEmail;
	}
	public Double getOldAmount() {
		return oldAmount;
	}
	public void setOldAmount(Double oldAmount) {
		this.oldAmount = oldAmount;
	}
	@Override
	public String toString() {
		return "RouteOrder [orderNo=" + orderNo + ", cusNo=" + cusNo
				+ ", orderNm=" + orderNm + ", status=" + status
				+ ", startdate=" + startdate + ", enddate=" + enddate
				+ ", adultCnt=" + adultCnt + ", childCnt=" + childCnt
				+ ", amount=" + amount + ", prepay=" + prepay
				+ ", evaluateflg=" + evaluateflg + ", delFlg=" + delFlg
				+ ", billTime=" + billTime + ", prepayTime=" + prepayTime
				+ ", allpayTime=" + allpayTime + ", routeJson=" + routeJson
				+ ", routeUsername=" + routeUsername + ", routeTel=" + routeTel
				+ ", routeEmail=" + routeEmail + ", oldAmount=" + oldAmount
				+ ", getOrderNo()=" + getOrderNo() + ", getCusNo()="
				+ getCusNo() + ", getOrderNm()=" + getOrderNm()
				+ ", getStatus()=" + getStatus() + ", getStartdate()="
				+ getStartdate() + ", getEnddate()=" + getEnddate()
				+ ", getAdultCnt()=" + getAdultCnt() + ", getChildCnt()="
				+ getChildCnt() + ", getAmount()=" + getAmount()
				+ ", getPrepay()=" + getPrepay() + ", getEvaluateflg()="
				+ getEvaluateflg() + ", getDelFlg()=" + getDelFlg()
				+ ", getBillTime()=" + getBillTime() + ", getPrepayTime()="
				+ getPrepayTime() + ", getAllpayTime()=" + getAllpayTime()
				+ ", getRouteJson()=" + getRouteJson()
				+ ", getRouteUsername()=" + getRouteUsername()
				+ ", getRouteTel()=" + getRouteTel() + ", getRouteEmail()="
				+ getRouteEmail() + ", getOldAmount()=" + getOldAmount()
				+ ", getCtdt()=" + getCtdt() + ", getCtuser()=" + getCtuser()
				+ ", getUpdt()=" + getUpdt() + ", getUpuser()=" + getUpuser()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
}
